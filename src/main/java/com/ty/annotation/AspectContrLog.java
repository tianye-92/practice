package com.ty.annotation;

import java.lang.annotation.*;

/**
 * Controller日志定义，主要解决controller层的操作日志
 * @author ouxiangfeng
 *
 */
@Retention(RetentionPolicy.RUNTIME) //注解在jvm加载的时候仍被保留
@Target({ElementType.METHOD}) //定义了注解声明在方法之前
@Documented
public @interface AspectContrLog {
	// 属性定义
	// 描述
	String descrption() default "";

	//操作类型，
	// 0：未定义(这里设计未定义，是考虑我们的系统新开发，可以直接从数据库查询了解那些方法未被解释)
	// 1：添加 2：修改 3：删除  4：查询
	String actionType() default "";
}

