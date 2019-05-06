package com.ty.test;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用于接收谷仓API返回信息，封装对象
 *
 * @ClassName GranaryResponseBase
 * @Author tianye
 * @Date 2019/4/28 17:44
 * @Version 1.0
 */
public class GranaryResponseBase {

    @ApiModelProperty(value = "响应标志，Success表示成功，Failure表示失败")
    private String ask;

    @ApiModelProperty(value = "消息提示")
    private String message;

    @ApiModelProperty(value = "错误信息")
    private ErrorInfo error;

    @ApiModelProperty(value = "分页信息")
    private Pagination pagination;

    @ApiModelProperty(value = "数据")
    private Object data;

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorInfo getError() {
        return error;
    }

    public void setError(ErrorInfo error) {
        this.error = error;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public class ErrorInfo{

        @ApiModelProperty(value = "错误信息内容")
        private String errMessage;

        @ApiModelProperty(value = "错误码")
        private String errCode;

        public String getErrMessage() {
            return errMessage;
        }

        public void setErrMessage(String errMessage) {
            this.errMessage = errMessage;
        }

        public String getErrCode() {
            return errCode;
        }

        public void setErrCode(String errCode) {
            this.errCode = errCode;
        }
    }

    public class Pagination{

        @ApiModelProperty(value = "分页大小")
        private String pageSize;

        @ApiModelProperty(value = "当前页")
        private String page;

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }
    }

}
