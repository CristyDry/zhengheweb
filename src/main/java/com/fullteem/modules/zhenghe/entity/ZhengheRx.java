/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.fullteem.common.persistence.DataEntity;

/**
 * 处方信息Entity
 * @author LeVis
 * @version 2017-09-25
 */
public class ZhengheRx extends DataEntity<ZhengheRx> {
	
	private static final long serialVersionUID = 1L;
	private String rxName;		// 处方名称
	private String rxNo;		// 处方编号
	private String payType;		// 付款类型
	private String patientId;		// 患者编号
	private String patientName;		// 患者名称
	private String patientGender;		// 患者性别
	private String patientAge;		// 患者年龄
	private String patientAddress;		// 患者地址
	private String patientPhone;		// 患者电话
	private String caseNo;		// 病例号
	private String category;		// 科别
	private String clinicalDiagnosis;		// 临床诊断
	private String doctor;		// 医师
	private String approvalDoctor;		// 审核药师
	private String deployDoctor;		// 调配药师/士
	private String checkDoctor;		// 核对、发药药师
	private BigDecimal totalAmount;		// 药品金额
	private String status;		// 状态
	private String departmentId;		// 所属部门
	private Date rxDate;		// 开具时间
	private Date processDate;		// 处理时间
	private Date processUser;		// 处理人
	private String deleteMark;		// 删除标记
	private String remark;		// 备注
	private String mender;		// 修改者
	private String creator;		// 创建者
	private List<ZhengheRxDetail> zhengheRxDetailList = Lists.newArrayList();		// 子表列表
	
	public ZhengheRx() {
		super();
	}

	public ZhengheRx(String id){
		super(id);
	}

	@Length(min=0, max=64, message="处方名称长度必须介于 0 和 64 之间")
	public String getRxName() {
		return rxName;
	}

	public void setRxName(String rxName) {
		this.rxName = rxName;
	}
	
	@Length(min=0, max=64, message="处方编号长度必须介于 0 和 64 之间")
	public String getRxNo() {
		return rxNo;
	}

	public void setRxNo(String rxNo) {
		this.rxNo = rxNo;
	}
	
	@Length(min=0, max=2, message="付款类型长度必须介于 0 和 2 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Length(min=0, max=64, message="患者编号长度必须介于 0 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Length(min=0, max=2, message="患者名称长度必须介于 0 和 2 之间")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	@Length(min=0, max=1, message="患者性别长度必须介于 0 和 1 之间")
	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	
	@Length(min=0, max=11, message="患者年龄长度必须介于 0 和 11 之间")
	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	
	@Length(min=0, max=120, message="患者地址长度必须介于 0 和 120 之间")
	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	
	@Length(min=0, max=21, message="患者电话长度必须介于 0 和 21 之间")
	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	
	@Length(min=0, max=64, message="病例号长度必须介于 0 和 64 之间")
	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	
	@Length(min=0, max=32, message="科别长度必须介于 0 和 32 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Length(min=0, max=255, message="临床诊断长度必须介于 0 和 255 之间")
	public String getClinicalDiagnosis() {
		return clinicalDiagnosis;
	}

	public void setClinicalDiagnosis(String clinicalDiagnosis) {
		this.clinicalDiagnosis = clinicalDiagnosis;
	}
	
	@Length(min=0, max=10, message="医师长度必须介于 0 和 10 之间")
	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	@Length(min=0, max=10, message="审核药师长度必须介于 0 和 10 之间")
	public String getApprovalDoctor() {
		return approvalDoctor;
	}

	public void setApprovalDoctor(String approvalDoctor) {
		this.approvalDoctor = approvalDoctor;
	}
	
	@Length(min=0, max=10, message="调配药师/士长度必须介于 0 和 10 之间")
	public String getDeployDoctor() {
		return deployDoctor;
	}

	public void setDeployDoctor(String deployDoctor) {
		this.deployDoctor = deployDoctor;
	}
	
	@Length(min=0, max=10, message="核对、发药药师长度必须介于 0 和 10 之间")
	public String getCheckDoctor() {
		return checkDoctor;
	}

	public void setCheckDoctor(String checkDoctor) {
		this.checkDoctor = checkDoctor;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=64, message="所属部门长度必须介于 0 和 64 之间")
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRxDate() {
		return rxDate;
	}

	public void setRxDate(Date rxDate) {
		this.rxDate = rxDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProcessUser() {
		return processUser;
	}

	public void setProcessUser(Date processUser) {
		this.processUser = processUser;
	}
	
	@Length(min=0, max=32, message="删除标记长度必须介于 0 和 32 之间")
	public String getDeleteMark() {
		return deleteMark;
	}

	public void setDeleteMark(String deleteMark) {
		this.deleteMark = deleteMark;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=32, message="修改者长度必须介于 0 和 32 之间")
	public String getMender() {
		return mender;
	}

	public void setMender(String mender) {
		this.mender = mender;
	}
	
	@Length(min=0, max=32, message="创建者长度必须介于 0 和 32 之间")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public List<ZhengheRxDetail> getZhengheRxDetailList() {
		return zhengheRxDetailList;
	}

	public void setZhengheRxDetailList(List<ZhengheRxDetail> zhengheRxDetailList) {
		this.zhengheRxDetailList = zhengheRxDetailList;
	}
}