package com.ty.quartz.entity;

public class QuartzPageModel extends BaseJobModel {

	private String jobDescription;// 任务描述
	private String cronExpression;// 任务计划
	private String createTime;// 创建时间
	private String jobStatus;// 任务状态
	private String dataMap;// 参数

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getDataMap() {
		return dataMap;
	}

	public void setDataMap(String dataMap) {
		this.dataMap = dataMap;
	}

}
