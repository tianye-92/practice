package com.ty.quartz.service.impl;

import com.ty.quartz.entity.QuartzModel;
import com.ty.quartz.entity.QuartzPageModel;
import com.ty.quartz.mapper.QuartzMapper;
import com.ty.quartz.service.QuartzService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class QuartzServiceImpl implements QuartzService {

	private Logger logger = LoggerFactory.getLogger(QuartzServiceImpl.class);

	/**
     * 调度器
	 */
	@Resource
	private Scheduler scheduler;

	@Resource
	private QuartzMapper quartzMapper;

	/**
	 * 所有任务列表
	 */
	public List<QuartzModel> list() {
		List<QuartzModel> list = new ArrayList<>();

		try {
			for (String groupJob : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(groupJob))) {
					List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
					for (Trigger trigger : triggers) {
						Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
						JobDetail jobDetail = scheduler.getJobDetail(jobKey);

						String cronExpression = "", createTime = "";

						if (trigger instanceof CronTrigger) {
							CronTrigger cronTrigger = (CronTrigger) trigger;
							cronExpression = cronTrigger.getCronExpression();
							createTime = cronTrigger.getDescription();
						}
						QuartzModel task = new QuartzModel();
						task.setJobName(jobKey.getName());
						task.setJobGroup(jobKey.getGroup());
						task.setJobDescription(jobDetail.getDescription());
						task.setJobStatus(triggerState.name());
						task.setCronExpression(cronExpression);
						task.setCreateTime(createTime);
						task.setDataMap(jobDetail.getJobDataMap().getString("dataMap"));
						task.setIsEdit("Y");
						list.add(task);
					}
				}
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	/**
	 * 保存定时任务
	 *
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void addJob(QuartzModel task) throws Exception {
		String jobName = task.getJobName(), jobGroup = task.getJobGroup(), cronExpression = task.getCronExpression(),
				jobDescription = task.getJobDescription(), dataMap = task.getDataMap(),
				createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		try {
			if (checkExists(jobName, jobGroup)) {
				throw new RuntimeException("添加任务失败任务已经存在" + jobGroup + jobName);
			}

			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

			CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
					.withMisfireHandlingInstructionDoNothing();
			// 触发器
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime)
					.withSchedule(schedBuilder).build();

			Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(jobName);
			JobDataMap jobData = new JobDataMap();
			jobData.put("dataMap", dataMap);
			// 任务数据
			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).withDescription(jobDescription)
					.setJobData(jobData).build();
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException | ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 修改定时任务
	 *
	 * @throws Exception
	 */
	public void edit(QuartzModel task) throws Exception {
		String jobName = task.getJobName(), jobGroup = task.getJobGroup(), cronExpression = task.getCronExpression(),
				jobDescription = task.getJobDescription(), dataMap = task.getDataMap(),
				createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		try {
			if (!checkExists(jobName, jobGroup)) {
				throw new RuntimeException("修改失败任务不存在" + jobGroup + jobName);
			}
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			JobKey jobKey = new JobKey(jobName, jobGroup);
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
					.withMisfireHandlingInstructionDoNothing();
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime)
					.withSchedule(cronScheduleBuilder).build();

			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			JobDataMap jobData = new JobDataMap();
			jobData.put("dataMap", dataMap);

			jobDetail = jobDetail.getJobBuilder().withDescription(jobDescription).setJobData(jobData).build();
			HashSet<Trigger> triggerSet = new HashSet<>();
			triggerSet.add(cronTrigger);

			scheduler.scheduleJob(jobDetail, triggerSet, true);
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 删除定时任务
	 *
	 * @param jobName
	 * @param jobGroup
	 */
	public void delete(String jobName, String jobGroup) {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		try {
			if (checkExists(jobName, jobGroup)) {
				scheduler.pauseTrigger(triggerKey);
				scheduler.unscheduleJob(triggerKey);
				logger.info("删除定时任务 triggerKey:{}, jobGroup:{}, jobName:{}", triggerKey, jobGroup, jobName);
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 暂停定时任务
	 *
	 * @param jobName
	 * @param jobGroup
	 */
	public void pause(String jobName, String jobGroup) {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		try {
			if (checkExists(jobName, jobGroup)) {
				scheduler.pauseTrigger(triggerKey);
				logger.info("暂停定时任务 triggerKey:{}, jobGroup:{}, jobName:{}", triggerKey, jobGroup, jobName);
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 重新开始任务
	 *
	 * @param jobName
	 * @param jobGroup
	 */
	public void resume(String jobName, String jobGroup) {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

		try {
			if (checkExists(jobName, jobGroup)) {
				scheduler.resumeTrigger(triggerKey);
				logger.info("重新开始任务 triggerKey:{}, jobGroup:{}, jobName:{}", triggerKey, jobGroup, jobName);
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 验证是否存在
	 *
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		return scheduler.checkExists(triggerKey);
	}

	/**
	 * 立即执行任务
	 *
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	@Override
	public void executeJob(String jobName, String jobGroup) throws SchedulerException {
		if (scheduler == null) {
			return;
		}
		JobKey key = new JobKey(jobName, jobGroup);
		scheduler.triggerJob(key);
	}

	/**
	 * 分页查询定时任务
	 */
	@Override
	public List<QuartzPageModel> pageList(QuartzPageModel taskPage) {
		return quartzMapper.pageList(taskPage);
	}
}
