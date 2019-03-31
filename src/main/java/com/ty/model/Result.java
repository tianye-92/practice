package com.ty.model;

/**
 * TODO
 *
 * @ClassName Result
 * @Author tianye
 * @Date 2019/3/22 23:53
 * @Version 1.0
 */
public class Result {

    private String name;
    private String sex;

    public Result() {

        System.out.println("进入result构造器");

    }

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
