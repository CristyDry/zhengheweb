package com.fullteem.modules.zhenghe.api.web;


import com.fullteem.common.utils.IdGen;
import com.fullteem.common.utils.StringUtils;
import com.fullteem.common.web.BaseController;
import com.fullteem.common.web.BaseResult;
import com.fullteem.modules.sys.entity.Office;
import com.fullteem.modules.sys.service.OfficeService;
import com.fullteem.modules.zhenghe.api.entity.request.RequestRx;
import com.fullteem.modules.zhenghe.api.entity.request.RequestRxDetail;
import com.fullteem.modules.zhenghe.api.entity.request.RequestTest;
import com.fullteem.modules.zhenghe.api.utils.CosmeticUpload;
import com.fullteem.modules.zhenghe.api.utils.ZhengheConstance;
import com.fullteem.modules.zhenghe.entity.*;
import com.fullteem.modules.zhenghe.service.*;
import com.wordnik.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        if (!StringUtils.isEmpty(requestRx.getDepartmentId())) {
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
            if(product == null){
                return buildFailedResultInfo(ZhengheConstance.productId_is_null);
            }
            ZhengheRxDetail rxDetail = new ZhengheRxDetail();
            rxDetail.setProductId(detail.getProductId());//商品编号
            rxDetail.setProductName(product.getProductName());//名称
            rxDetail.setNum(detail.getNum());//数量
            rxDetail.setPrice(new BigDecimal(product.getPrice() == null? 0d : product.getPrice()));//价格
            rxDetail.setDepartmentId(zhengheRx.getDepartmentId());//药店
            rxDetail.setCreator(zhengheRx.getCreator());//创建人
            rxDetail.setSig(detail.getSig());//备注
            rxDetail.setStandard(product.getStandard());//规格
            rxDetail.setId(IdGen.uuid());
            rxDetail.setCreateDate(new Date());
            totalAmout = totalAmout.add(rxDetail.getPrice().multiply(new BigDecimal(rxDetail.getNum())));//合计
            listDetails.add(rxDetail);
        }
        zhengheRx.setTotalAmount(totalAmout);
        //明细
        zhengheRx.setZhengheRxDetailList(listDetails);
        //保存数据
        zhengheRxService.save(zhengheRx);

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


}