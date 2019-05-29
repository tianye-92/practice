//package com.ty.quartz.service.impl;
//
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.ty.quartz.entity.QuartzLogModel;
//import com.ty.quartz.entity.TotalModel;
//import com.ty.quartz.mapper.QuartzMapper;
//import com.ty.quartz.service.TaskLogService;
//import com.ty.utils.DateUtil;
//import org.apache.commons.lang3.time.DateFormatUtils;
//import org.quartz.JobExecutionContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@Service
//public class TaskLogServiceImpl implements TaskLogService {
//
//	private Logger logger = LoggerFactory.getLogger(TaskLogServiceImpl.class);
//
//	@Resource
//	private QuartzMapper quartzMapper;
//
//	@Override
//	public void insertTask(JobExecutionContext context, String message, Long time) {
//		QuartzLogModel task = new QuartzLogModel() {
//			{
//				setExecuteTime(DateFormatUtils.format(context.getScheduledFireTime(), DateUtil.FORMAT_2));
//				setCreateTime(DateFormatUtils.format(context.getTrigger().getStartTime(), DateUtil.FORMAT_2));
//				setDescription(context.getJobDetail().getDescription());
//				setClassName(context.getTrigger().getJobKey().getName());
//				setTaskName(context.getTrigger().getJobKey().getGroup());
//				setData(JSONObject.toJSONString(context.getJobDetail().getJobDataMap()));
//				setMessage(message);
//				setTaskStatus(String.valueOf(dataStatus(message)));
//				setRunTime(String.valueOf(time));
//			}
//		};
//		quartzMapper.insertLog(task);
//		logger.info("定时任务执行记录: {}", JSONObject.toJSONString(task));
//	}
//
//	@Override
//	public List<QuartzLogModel> findTaskLog(QuartzLogModel quartzLogModel) {
//		return quartzMapper.findTaskLog(quartzLogModel);
//	}
//
//	public static boolean dataStatus(String message) {
//		if (message.toLowerCase().contains("exception in thread"))
//			return false;
//		try {
//			JSONObject json = JSONObject.parseObject(message);
//			if (json.containsKey("success"))
//				return json.getBoolean("success");
//			return true;
//		} catch (JSONException | NullPointerException e) {
//			String data = message.replaceAll(" ", "").toLowerCase();
//			if (data.contains("\"success\":false"))
//				return false;
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
//
//	@Override
//	public List<TotalModel> findDayTotal(Integer type) {
//		return quartzMapper.findDayTotal(type);
//	}
//
//	@Override
//	public TotalModel findTotal(TotalModel total) {
//		return quartzMapper.findTotal(total);
//	}
//
//}
