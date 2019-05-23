package com.ty.quartz.job;

import com.ty.quartz.service.TaskLogService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * job基础类，通过传入实际job全限定名，反射得到
 *
 * @ClassName QuartzJobFactoryConfig
 * @Author tianye
 * @Date 2019/5/22 10:22
 * @Version 1.0
 */
@Component
@DisallowConcurrentExecution
public abstract class BaseQuartzJob implements Job {

	public String message = null;

	@Resource
	private TaskLogService taskLogService;

	public abstract void executeJob(JobExecutionContext context) throws Exception;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		long startTime = System.currentTimeMillis();
		long runTime = 0;
		try {
			executeJob(context);
			long endTime = System.currentTimeMillis();
			runTime = (endTime - startTime) / 1000;
		} catch (Exception e) {
			long endTime = System.currentTimeMillis();
			runTime = (endTime - startTime) / 1000;
			message = ExceptionUtils.getStackTrace(e);
		}
		taskLogService.insertTask(context, message, runTime);
	}

}
