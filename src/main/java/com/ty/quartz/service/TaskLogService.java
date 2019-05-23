package com.ty.quartz.service;

import com.ty.quartz.entity.QuartzLogModel;
import com.ty.quartz.entity.TotalModel;
import org.quartz.JobExecutionContext;

import java.util.List;

public interface TaskLogService {

    void insertTask(JobExecutionContext context, String message, Long time);

    /**
     * 查询log列表
     *
     * @param quartzLogModel
     * @return
     */
    List<QuartzLogModel> findTaskLog(QuartzLogModel quartzLogModel);

    /**
     * 查询最近七天统计
     *
     * @return
     */
    List<TotalModel> findDayTotal(Integer type);

    /**
     * 查询总数
     *
     * @return
     */
    TotalModel findTotal(TotalModel total);

}
