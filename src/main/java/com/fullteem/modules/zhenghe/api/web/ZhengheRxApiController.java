package com.fullteem.modules.zhenghe.api.web;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fullteem.common.utils.DateUtils;
import com.fullteem.common.utils.StringUtils;
import com.fullteem.common.web.BaseController;
import com.fullteem.common.web.BaseResult;
import com.fullteem.modules.sys.entity.Office;
import com.fullteem.modules.sys.service.OfficeService;
import com.fullteem.modules.zhenghe.api.entity.request.*;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.fullteem.modules.zhenghe.entity.*;
import com.fullteem.modules.zhenghe.service.*;
import com.wordnik.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 类名: TestApiController</br>
 * 包名：com.fullteem.modules.cosmetic.api.web </br>
 * 描述: 上传测试</br>
 * 发布版本号：</br>
 * 开发人员： 朱恋青</br>
 * 创建时间： 2015年11月2日
 */
@Api(value = "ZhengheRx", description = "处方")
@Controller
@RequestMapping(value = "/api/ZhengheRx")
public class ZhengheRxApiController extends BaseController {

    @Autowired
    ZhengheRxService zhengheRxService;
    @Autowired
    ZhengheDoctorService zhengheDoctorService;
    @Autowired
    OfficeService officeService;
    @Autowired
    ZhenghePatientService patientService;
    @Autowired
    ZhengheProductService productService;
    @Autowired
    ZhengheDepartmentsService departmentsService;
    @Autowired
    ZhengheHospitalService hospitalService;
    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "开具处方", notes = "创建处方", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = ZhengheConstance.param_fault, message = "参数格式不正确", response = String.class),
            @ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class),
            @ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class)
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> create(@ApiParam(required = true, value = "")
                                             @RequestBody RequestRx requestRx) {
        if (requestRx.getZhengheRxDetailList() == null || requestRx.getZhengheRxDetailList().isEmpty()) {
            return buildFailedResultInfo(ZhengheConstance.param_fault);
        }
        if (StringUtils.isEmpty(requestRx.getDepartmentId())) {
            return buildFailedResultInfo(ZhengheConstance.departmentId_not_exist);
        }
        ZhengheRx zhengheRx = new ZhengheRx();
        //拷贝属性
        BeanUtils.copyProperties(requestRx, zhengheRx);
        //获取取医生
        ZhengheDoctor zhengheDoctor = zhengheDoctorService.get(zhengheRx.getCreator());
        if (zhengheDoctor == null) {
            return buildFailedResultInfo(ZhengheConstance.doctor_not_exist);
        }
        //获取科室
        ZhengheDepartments department = departmentsService.get(zhengheDoctor.getDepartmentsId());
        if (department == null) {
            return buildFailedResultInfo(ZhengheConstance.departmentId_not_exist);
        }
        //获取医院
        ZhengheHospital hospital = hospitalService.get(zhengheDoctor.getHospitalId());
        if (hospital == null) {
            return buildFailedResultInfo(ZhengheConstance.param_fault);
        }

        zhengheRx.setDoctor(zhengheDoctor.getDoctorName());//医生名称
        zhengheRx.setCategory(department.getDepartmentsName());//科室
        zhengheRx.setRxName(hospital.getHospitalName() + "处方笺");//处方名称
        zhengheRx.setRxNo(makeRxNo());//设置处方号
        zhengheRx.setPayType("1");//自费
        zhengheRx.setStatus("0");//待接收
        zhengheRx.setCreateDate(new Date());//开单时间
        zhengheRx.setRxDate(new Date());//开单时间
        //获取患者信息
        if (!StringUtils.isEmpty(zhengheRx.getPatientId())) {
            ZhenghePatient patient = patientService.get(zhengheRx.getPatientId());
            if (patient == null) {
                return buildFailedResultInfo(ZhengheConstance.param_fault);
            }
            zhengheRx.setPatientName(patient.getPatientName());//患者名称
        }
        BigDecimal totalAmout = BigDecimal.ZERO;
        List<ZhengheRxDetail> listDetails = new ArrayList<ZhengheRxDetail>();
        //处理明细
        for (RequestRxDetail detail : requestRx.getZhengheRxDetailList()) {
            //获取商品信息
            ZhengheProduct product = productService.get(detail.getProductId());
            if (product == null) {
                return buildFailedResultInfo(ZhengheConstance.productId_is_null);
            }
            ZhengheRxDetail rxDetail = new ZhengheRxDetail();
            rxDetail.setProductId(detail.getProductId());//商品编号
            rxDetail.setProductName(product.getProductName());//名称
            rxDetail.setNum(detail.getNum());//数量
            rxDetail.setPrice(new BigDecimal(product.getPrice() == null ? 0d : product.getPrice()));//价格
            rxDetail.setDepartmentId(zhengheRx.getDepartmentId());//药店
            rxDetail.setCreator(zhengheRx.getCreator());//创建人
            rxDetail.setSig(detail.getSig());//备注
            rxDetail.setStandard(product.getStandard());//规格
            //rxDetail.setId(IdGen.uuid());
            rxDetail.setCreateDate(new Date());
            totalAmout = totalAmout.add(rxDetail.getPrice()
                    .multiply(new BigDecimal(Integer.parseInt(rxDetail.getNum()))));//合计
            listDetails.add(rxDetail);
        }
        zhengheRx.setTotalAmount(totalAmout.setScale(2,BigDecimal.ROUND_HALF_UP));
        //明细
        zhengheRx.setZhengheRxDetailList(listDetails);
        //保存数据
        zhengheRxService.save(zhengheRx);
        zhengheRx.setZhengheRxDetailList(null);
        return buildSuccessResultInfo(zhengheRx);
    }

    /*
     * 生成订单'项'表中不重复的订单号
     */
    private String makeRxNo() {
        String rxNo = System.currentTimeMillis() + "";
        ZhengheRx zhengheRx = new ZhengheRx();
        zhengheRx.setRxNo(rxNo);
        if (!zhengheRxService.findList(zhengheRx).isEmpty()) {
            rxNo = makeRxNo();
        }
        return rxNo;
    }


    @ApiOperation(value = "药店列表", notes = "选中药店", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class),
            @ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class)
    })
    @RequestMapping(value = "/office", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> office() {
        Office office = new Office();
        office.setParentIds("0,1");
        List<Office> list = officeService.findList(office);
        Map<String, JSONObject> root = new HashMap<String, JSONObject>();
        for (Office f : list) {
            if ("1".equals(f.getParentId())) {
                JSONObject json = new JSONObject();
                json.put("id", f.getId());
                json.put("name", f.getName());
                root.put(f.getId(), json);
            }
        }
        for (Office f : list) {
            if (!"1".equals(f.getParentId())) {

                JSONObject json;
                if ((json = root.get(f.getParentId())) == null) {
                    continue;
                }
                JSONArray childs;
                if ((childs = json.getJSONArray("childs")) == null) {
                    childs = new JSONArray();
                    json.put("childs", childs);
                }
                JSONObject child = new JSONObject();
                child.put("id", f.getId());
                child.put("name", f.getName());
                childs.add(child);
            }
        }
        return buildSuccessResultInfo(root.values());
    }

    @ApiOperation(value = "处方列表", notes = "查一个月最多100条数据", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class),
            @ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class)
    })
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> rxList(@ApiParam(required = true, value = "")
                                             @RequestBody RequestRxList requestRx) {
        //查一个月最多100条数据
        ZhengheRx rx = new ZhengheRx();
        rx.setCreator(requestRx.getId());
        rx.setStatus(requestRx.getStatus());
        rx.setUpdateDate(DateUtils.addMonths(new Date(), -1));
        List<ZhengheRx> rxList = zhengheRxService.findList(rx);
        for (ZhengheRx r : rxList) {
            ZhengheRxDetail detail = new ZhengheRxDetail();
            detail.setRxId(r.getId());
            //获取明细
            r.setZhengheRxDetailList(zhengheRxService.findDetailList(detail));
            for (ZhengheRxDetail detail1 : r.getZhengheRxDetailList()) {
                if (!(detail1.getImgUrl() != null && detail1.getImgUrl().startsWith("http"))) {
                    detail1.setImgUrl(getBasePath() + detail1.getImgUrl());
                }

            }
        }
        return buildSuccessResultInfo(rxList);
    }

    @ApiOperation(value = "取消处方", notes = "", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class),
            @ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class)
    })
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> cancel(@ApiParam(required = true, value = "处方id")
                                             @RequestBody RequestId id) {

        ZhengheRx rx = zhengheRxService.get(id.getId());
        if(rx == null){
            return buildFailedResultInfo(ZhengheConstance.orderId_not_exist);
        }
        if("0".equals(rx.getStatus())){
            //待接收才能取消
            rx.setStatus("3");//医生取消
            zhengheRxService.save(rx);
        }
        return buildSuccessResultInfo(ZhengheConstance.BASE_SUCCESS_CODE);
    }

    @ApiOperation(value = "处方统计", notes = "", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = ZhengheConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class),
            @ApiResponse(code = ZhengheConstance.BASE_FAIL_CODE, message = "失败", response = String.class)
    })
    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity<BaseResult> report(@ApiParam(required = true, value = "")
                                             @RequestBody RequestDoctorReport report) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("creator", report.getId());
        map.put("startDate", report.getStartDate());
        map.put("endDate", report.getEndDate());

        List<DoctorReport> list = zhengheRxService.reportApiDoctor(map);

        Map<String, DoctorReport> header = new HashMap<String, DoctorReport>();
        //建树
        for (DoctorReport doctorReport : list){
            DoctorReport head;
            if((head = header.get(doctorReport.getClassifyName())) == null){
                head = new DoctorReport();
                head.setClassifyName(doctorReport.getClassifyName());
                head.setNums(0);
                header.put(doctorReport.getClassifyName(), head);
            }
            doctorReport.setClassifyName(null);
            //汇总
            head.setNums(doctorReport.getNums() + head.getNums());
            head.getChild().add(doctorReport);
        }
        return buildSuccessResultInfo(header.values());
    }


}