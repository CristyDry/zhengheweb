package com.fullteem.modules.zhenghe.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeWis on 2017/10/11.
 */
public class DoctorReport implements Serializable {
    private String productName;
    private String classifyName;
    private Integer nums;
    private List<DoctorReport> child = new ArrayList<DoctorReport>();

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<DoctorReport> getChild() {
        return child;
    }

    public void setChild(List<DoctorReport> child) {
        this.child = child;
    }
}
