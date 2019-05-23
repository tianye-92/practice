package com.ty.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Quartz配置类
 *
 * @ClassName QuartzJobFactoryConfig
 * @Author tianye
 * @Date 2019/5/22 10:22
 * @Version 1.0
 */
@Configuration
//@MapperScan(basePackages = "com.rondaful.cloud.task.mapper", sqlSessionTemplateRef = "taskSqlSessionTemplate")
public class QuartzConfig {

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(QuartzJobFactoryConfig jobFactory) throws Exception {
	    // 创建一个调度器工厂bean
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		// 配置数据源
		schedulerFactoryBean.setDataSource(getDataSource());
		// 设置是否任意一个已定义的Job会覆盖现在的Job。默认为false，即已定义的Job不会覆盖现有的Job。
		schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动10秒后  ，定时器才开始启动
        schedulerFactoryBean.setStartupDelay(10);
        // 通过spring bean工厂注入job实例
        schedulerFactoryBean.setJobFactory(jobFactory);
        // 加载quartz配置文件
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
		return schedulerFactoryBean;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		// 初始化bean的时候执行
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	@Bean
	public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws IOException, SchedulerException {
	    // 通过调度器工厂生成一个调度器实例
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.start();
		return scheduler;
	}

	// ====================================定时任务框架数据源相关配置=============================================

	@Bean(name = "taskDataSource")
	@ConfigurationProperties(prefix = "spring.task.datasource")
	public DataSource getDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "taskSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("taskDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

	@Bean(name = "taskSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("taskSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

	@Bean("taskTxManager")
    public PlatformTransactionManager annotationDrivenTransactionManager(@Qualifier("taskDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
