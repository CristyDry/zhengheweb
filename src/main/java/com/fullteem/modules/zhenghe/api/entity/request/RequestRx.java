package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by LeWis on 2017/9/25.
 */
@ApiModel(value = "添加处方", description = "类型:String")
public class RequestRx {
    @ApiModelProperty(value = "患者id", required = false)
    private String patientId;        // 患者编号
    @ApiModelProperty(value = "患者名称", required = true)
    private String patientName;        // 患者名称
    @ApiModelProperty(value = "性别", required = false)
    private String patientGender;        // 患者性别
    @ApiModelProperty(value = "患者年龄", required = false)
    private String patientAge;        // 患者年龄
    @ApiModelProperty(value = "患者地址", required = false)
    private String patientAddress;        // 患者地址
    @ApiModelProperty(value = "患者电话", required = false)
    private String patientPhone;        // 患者电话
    @ApiModelProperty(value = "病例号", required = false)
    private String caseNo;        // 病例号
    @ApiModelProperty(value = "临床诊断", required = true)
    private String clinicalDiagnosis;        // 临床诊断
    @ApiModelProperty(value = "审核药师", required = false)
    private String approvalDoctor;        // 审核药师
    @ApiModelProperty(value = "调配药师/士", required = false)
    private String deployDoctor;        // 调配药师/士
    @ApiModelProperty(value = "核对、发药药师", required = false)
    private String checkDoctor;        // 核对、发药药师
    @ApiModelProperty(value = "医生编号", required = false)
    private String creator;        // 创建者
    @ApiModelProperty(value = "处方门店（所属部门）", required = false)
    private String departmentId;        // 所属部门
    @ApiModelProperty(value = "处方信息", required = false)
    private List<RequestRxDetail> zhengheRxDetailList;        // 子表列表

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    public void setClinicalDiagnosis(String clinicalDiagnosis) {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    public String getApprovalDoctor() {
        return approvalDoctor;
    }

    public void setApprovalDoctor(String approvalDoctor) {
        this.approvalDoctor = approvalDoctor;
    }

    public String getDeployDoctor() {
        return deployDoctor;
    }

    public void setDeployDoctor(String deployDoctor) {
        this.deployDoctor = deployDoctor;
    }

    public String getCheckDoctor() {
        return checkDoctor;
    }

    public void setCheckDoctor(String checkDoctor) {
        this.checkDoctor = checkDoctor;
    }

    public List<RequestRxDetail> getZhengheRxDetailList() {
        return zhengheRxDetailList;
    }

    public void setZhengheRxDetailList(List<RequestRxDetail> zhengheRxDetailList) {
        this.zhengheRxDetailList = zhengheRxDetailList;
    }
}
