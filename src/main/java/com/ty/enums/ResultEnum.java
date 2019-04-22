package com.ty.enums;

/**
 * 异常信息枚举类
 *
 * @ClassName ResultEnum
 * @Author tianye
 * @Date 2019/4/22 10:16
 * @Version 1.0
 */
public enum ResultEnum {

    RESULT_CODE_100001("100001","用户名或密码错误"),
    RESULT_CODE_100400("100400","请求参数错误"),
    RESULT_CODE_100401("100401","权限不足"),
    RESULT_CODE_100403("100403","参数不允许为空"),
    RESULT_CODE_100406("100406","未登录"),

    RESULT_CODE_100200("100200","请求成功"),
    RESULT_CODE_100500("100500","系统异常"),
    RESULT_CODE_999999("999999","{0}");

    private String code;
    private String message;

    private ResultEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
