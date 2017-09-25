package com.fullteem.modules.zhenghe.api.entity.request;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by LeWis on 2017/9/25.
 */
@ApiModel(value = "添加处方明细", description = "类型:String")
public class RequestRxDetail {
    @ApiModelProperty(value = "商品编号", required = true)
    private String productId;        // 商品编号
    @ApiModelProperty(value = "数量", required = true)
    private String num;        // 数量
    @ApiModelProperty(value = "使用说明", required = false)
    private String sig;        // 使用说明

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }
}
