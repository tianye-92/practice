package com.ty.model.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequestVo {
	@ApiModelProperty(value = "卖家名称   [没有则传SellerAccount]", required = true)
	private String sellerName;
	@ApiModelProperty(value = "商品金额", required = true)
	private BigDecimal productAmount;
	@ApiModelProperty(value = "预估物流费用", required = true)
	private BigDecimal logisticsFare;
	@ApiModelProperty(value = "卖家ID", required = true)
	private Integer sellerId;
	@ApiModelProperty(value = "订单号   [唯一]", required = true)
	private String orderNo;
	@ApiModelProperty(value = "卖家账户", required = true)
	private String sellerAccount;
	@ApiModelProperty(value = "物流商ID", required = true)
	private String logisticsId;
	@ApiModelProperty(value = "物流商名称", required = true)
	private String logisticsName;
	@ApiModelProperty(value = "仓库ID", required = true)
	private String storageId;
	@ApiModelProperty(value = "仓库名称", required = true)
	private String storageName;
	@ApiModelProperty(value = "供应链公司ID V2.0新增", required = true)
	private Integer supplyCompanyId;
	@ApiModelProperty(value = "供应链公司名称  V2.0新增", required = true)
	private String supplyCompanyName;


	public String getSellerAccount() {
		return sellerAccount;
	}

	public void setSellerAccount(String sellerAccount) {
		this.sellerAccount = sellerAccount;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public BigDecimal getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}

	public BigDecimal getLogisticsFare() {
		return logisticsFare;
	}

	public void setLogisticsFare(BigDecimal logisticsFare) {
		this.logisticsFare = logisticsFare;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public Integer getSupplyCompanyId() {
		return supplyCompanyId;
	}

	public void setSupplyCompanyId(Integer supplyCompanyId) {
		this.supplyCompanyId = supplyCompanyId;
	}

	public String getSupplyCompanyName() {
		return supplyCompanyName;
	}

	public void setSupplyCompanyName(String supplyCompanyName) {
		this.supplyCompanyName = supplyCompanyName;
	}

}
