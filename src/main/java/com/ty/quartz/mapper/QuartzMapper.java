package com.ty.quartz.mapper;

import com.ty.quartz.entity.QuartzLogModel;
import com.ty.quartz.entity.QuartzPageModel;
import com.ty.quartz.entity.TotalModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Quartz针对数据的增删改查
 *
 * @ClassName QuartzJobFactoryConfig
 * @Author tianye
 * @Date 2019/5/22 10:22
 * @Version 1.0
 */
public interface QuartzMapper {

	List<QuartzPageModel> pageList(QuartzPageModel taskPage);

	void insertLog(QuartzLogModel log);

	List<QuartzLogModel> findTaskLog(QuartzLogModel log);

	List<TotalModel> findDayTotal(@Param(value = "type") Integer type);

	TotalModel findTotal(TotalModel total);

}