package com.ty.quartz.service;

import com.ty.quartz.entity.QuartzModel;
import com.ty.quartz.entity.QuartzPageModel;
import org.quartz.SchedulerException;

import java.util.List;

public interface QuartzService {

	List<QuartzPageModel> pageList(QuartzPageModel taskPage);

	void addJob(QuartzModel task) throws Exception;

	void edit(QuartzModel task) throws Exception;

	void executeJob(String jobName, String jobGroup) throws SchedulerException;

	void delete(String jobName, String jobGroup);

	void pause(String jobName, String jobGroup);

	void resume(String jobName, String jobGroup);

	boolean checkExists(String jobName, String jobGroup) throws SchedulerException;

}
