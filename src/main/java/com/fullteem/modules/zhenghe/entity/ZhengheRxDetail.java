/**
 * Copyright &copy; 2012-2014 <a href="http://www.fullteem.com">fullteem_master</a> All rights reserved.
 */
package com.fullteem.modules.zhenghe.entity;

import org.hibernate.validator.constraints.Length;

import com.fullteem.common.persistence.DataEntity;

import java.math.BigDecimal;

/**
 * 处方信息Entity
 *
 * @author LeVis
 * @version 2017-09-28
 */
public class ZhengheRxDetail extends DataEntity<ZhengheRxDetail> {

    private static final long serialVersionUID = 1L;
    private String rxId;        // 处方编号 父类
    private String departmentId;        // 所属部门
    private String productId;        // 商品编号
    private String productName;        // 药名
    private BigDecimal price;        // 价格
    private String num;        // 数量
    private String standard;        // 规格
    private String sig;        // 使用说明
    private String deleteMark;        // 删除标记
    private String remark;        // 备注
    private String mender;        // 修改者
    private String creator;        // 创建者
    private String imgUrl;         //图片

    public ZhengheRxDetail() {
        super();
    }

    public ZhengheRxDetail(String id) {
        super(id);
    }

    @Length(min = 1, max = 64, message = "处方编号长度必须介于 1 和 64 之间")
    public String getRxId() {
        return rxId;
    }

    public void setRxId(String rxId) {
        this.rxId = rxId;
    }

    @Length(min = 0, max = 64, message = "所属部门长度必须介于 0 和 64 之间")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Length(min = 0, max = 64, message = "商品编号长度必须介于 0 和 64 之间")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Length(min = 0, max = 64, message = "药名长度必须介于 0 和 64 之间")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Length(min = 0, max = 11, message = "数量长度必须介于 0 和 11 之间")
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Length(min = 0, max = 64, message = "规格长度必须介于 0 和 64 之间")
    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Length(min = 0, max = 64, message = "使用说明长度必须介于 0 和 64 之间")
    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    @Length(min = 0, max = 32, message = "删除标记长度必须介于 0 和 32 之间")
    public String getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(String deleteMark) {
        this.deleteMark = deleteMark;
    }

    @Length(min = 0, max = 255, message = "备注长度必须介于 0 和 255 之间")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Length(min = 0, max = 32, message = "修改者长度必须介于 0 和 32 之间")
    public String getMender() {
        return mender;
    }

    public void setMender(String mender) {
        this.mender = mender;
    }

    @Length(min = 0, max = 32, message = "创建者长度必须介于 0 和 32 之间")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}