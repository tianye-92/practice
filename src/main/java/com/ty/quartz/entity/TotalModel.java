package com.ty.quartz.entity;

public class TotalModel {

	private String date;
	private String total;
	private String success;
	private String failure;
	private boolean platform = false;

	public TotalModel() {

	}

	public TotalModel(boolean platform) {
		this.platform = platform;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getFailure() {
		return failure;
	}

	public void setFailure(String failure) {
		this.failure = failure;
	}

	public boolean isPlatform() {
		return platform;
	}

	public void setPlatform(boolean platform) {
		this.platform = platform;
	}

}
