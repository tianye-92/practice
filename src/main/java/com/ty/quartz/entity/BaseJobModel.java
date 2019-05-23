package com.ty.quartz.entity;

import io.swagger.annotations.ApiModelProperty;

public class BaseJobModel {

	@ApiModelProperty(value = "为True 为平台, False其他任务")
	public boolean platform = false;// 为True 为平台, False其他任务

	@ApiModelProperty(value = "任务名称,实现继承RondafulQuartzJob类的class")
	public String jobName;

	@ApiModelProperty(value = "任务名")
	public String jobGroup;

	public boolean isPlatform() {
		return platform;
	}

	public void setPlatform(boolean platform) {
		this.platform = platform;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

}
