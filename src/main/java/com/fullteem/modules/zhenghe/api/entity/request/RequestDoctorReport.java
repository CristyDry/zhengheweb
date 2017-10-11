package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "医生统计", description = "医生统计")
public class RequestDoctorReport {

    @ApiModelProperty(value = "医生Id", required = true)
    private String id;
    @ApiModelProperty(value = "开始时间：yyyy-MM-dd", required = true, notes = "yyyy-MM-dd")
    private Date startDate;
    @ApiModelProperty(value = "结束时间：yyyy-MM-dd", required = true, notes = "yyyy-MM-dd")
    private Date endDate;


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
