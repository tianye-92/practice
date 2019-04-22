package com.ty.exception;

import com.ty.enums.ResultEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 自定义统一异常类
 *
 * @ClassName ExceptionTy
 * @Author tianye
 * @Date 2019/4/22 10:09
 * @Version 1.0
 */
public class ExceptionTy extends RuntimeException{

    /**
     * 错误码
     */
    private String RetCode;

    /**
     * 错误信息
     */
    private String RetMessage;

    public ExceptionTy(String code, String message){
        super(StringUtils.join("code:",code," --> ","message:",message));
        this.setRetCode(code);
        this.setRetMessage(message);
    }

    public ExceptionTy(ResultEnum resultEnum){
        this(resultEnum.getCode(),resultEnum.getMessage());
    }

    public ExceptionTy(ResultEnum resultEnum,String message){
        this(resultEnum.getCode(),message);
    }

    public ExceptionTy(String message) {
        this(ResultEnum.RESULT_CODE_999999,message);
    }

    public String getRetCode() {
        return RetCode;
    }

    public void setRetCode(String retCode) {
        RetCode = retCode;
    }

    public String getRetMessage() {
        return RetMessage;
    }

    public void setRetMessage(String retMessage) {
        RetMessage = retMessage;
    }
}
