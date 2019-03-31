package com.ty.model;

/**
 * TODO
 *
 * @ClassName Request
 * @Author tianye
 * @Date 2019/3/23 0:03
 * @Version 1.0
 */
public class Request {

    private Integer inputType;

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
