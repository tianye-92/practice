package com.ty.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统记录
 * @author Administrator
 *
 */
public class
SysLog implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 4109357155591597203L;

    @ApiModelProperty(value = "id", required = false)
    private Integer id;//主键id

    @ApiModelProperty(value = "用户名", required = false)
    private String username;

    @ApiModelProperty(value = "用户昵称", required = false)
    @Deprecated
    private String account;//账户

    @ApiModelProperty(value = "用户ip地址", required = false)
    private String clientIp;//用户ip地址

    @ApiModelProperty(value = "操作路径", required = false)
    private String optionUrl;//'操作URL'

    @ApiModelProperty(value = "操作时间", required = false)
    private Date createDate;//创建日期

    @ApiModelProperty(value = "操作提交的参数", required = false)
    private String optionParams;//操作参数

    @ApiModelProperty(value = "注释", required = false)
    private String note;//注释

    @ApiModelProperty(value = "操作的类型：增，删，改，查", required = false)
    private String optionActiontype;//选择操作类型

    @ApiModelProperty(value = "操作内容", required = false)
    private String optionDescrption;//选择说明

    @ApiModelProperty(value = "平台", required = false)
    private String platform;//平台

    @ApiModelProperty(value = "用户token", required = false)
    private String userToken;

    @ApiModelProperty(value = "当前操作路径", required = false)
    private String url ;

    @ApiModelProperty(value = "当前操作路径", required = false)
    private String loginName ;

    @ApiModelProperty(value = "当前用户类型", required = false)
    private Integer platformType;

    public Integer getPlatformType() {
        return platformType;
    }

    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SysLog() {
        super();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }






    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }

    public String getOptionUrl() {
        return optionUrl;
    }

    public void setOptionUrl(String optionUrl) {
        this.optionUrl = optionUrl == null ? null : optionUrl.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOptionParams() {
        return optionParams;
    }

    public void setOptionParams(String optionParams) {
        this.optionParams = optionParams == null ? null : optionParams.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getOptionActiontype() {
        return optionActiontype;
    }

    public void setOptionActiontype(String optionActiontype) {
        this.optionActiontype = optionActiontype;
    }

    public String getOptionDescrption() {
        return optionDescrption;
    }

    public void setOptionDescrption(String optionDescrption) {
        this.optionDescrption = optionDescrption == null ? null : optionDescrption.trim();
    }
}