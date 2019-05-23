package com.ty.quartz.entity;

import com.ty.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

@ApiModel(description = "Task")
public class QuartzModel extends BaseJobModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "任务描述")
	private String jobDescription;

	@ApiModelProperty(value = "任务状态[此参数仅用作查看]")
	private String jobStatus;

	@ApiModelProperty(value = "任务表达式[cronExpression 与 cronDate] 优先选用cronExpression 其次 cronDate")
	private String cronExpression;

	@ApiModelProperty(value = "任务执行时间格式yyyy-MM-dd HH:mm:ss[cronExpression 与 cronDate] 优先选用cronExpression 其次 cronDate")
	private String cronDate;

	@ApiModelProperty(value = "创建时间[此参数仅用作查看]")
	private String createTime;

	@ApiModelProperty(value = "是否为修改,新增为N.修改为Y[新增修改时需要]")
	private String isEdit = "Y";

	@ApiModelProperty(value = "执行JOB时,JOB所需参数")
	private String dataMap;

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		if (StringUtils.isBlank(this.cronExpression)) {
			try {
				if (StringUtils.isBlank(this.cronDate))
					return DateUtil.init(DateUtil.FORMAT_CRON).format(new Date());
				else
					return DateUtil.init(DateUtil.FORMAT_CRON).format(DateUtil.init(DateUtil.FORMAT_2).parse(this.cronDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
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

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getDataMap() {
		return dataMap;
	}

	public void setDataMap(String dataMap) {
		this.dataMap = dataMap;
	}

	public String getCronDate() {
		return cronDate;
	}

	public void setCronDate(String cronDate) {
		this.cronDate = cronDate;
	}

}