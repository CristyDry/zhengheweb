package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by LeWis on 2017/9/25.
 */
@ApiModel(value = "查询处方", description = "类型:String")
public class RequestRxList {
    @ApiModelProperty(value = "医生id", required = true)
    private String Id;        // 患者编号
    @ApiModelProperty(value = "状态", required = true, notes = "0待接收，1已完成，2已取消，3医生取消")
    private String status;        // 患者编号

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
