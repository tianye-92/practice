//package com.ty.config;
//
//import org.quartz.spi.TriggerFiredBundle;
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.scheduling.quartz.AdaptableJobFactory;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
///**
// * 创建job 实例工厂，解决spring注入问题，如果使用默认会导致spring的@Autowired 无法注入问题
// *
// * @ClassName QuartzJobFactoryConfig
// * @Author tianye
// * @Date 2019/5/22 10:22
// * @Version 1.0
// */
//@Component
//public class QuartzJobFactoryConfig extends AdaptableJobFactory {
//
//    @Resource
//    private AutowireCapableBeanFactory capableBeanFactory;
//
//    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
//        Object jobInstance = super.createJobInstance(bundle);
//        capableBeanFactory.autowireBean(jobInstance);
//        return jobInstance;
//    }
//}
