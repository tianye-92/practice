package com.ty.utils;

import com.ty.model.vo.OrderRequestVo;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 参数校验工具类，通过反射得到实体类字段上的swagger注解，通过判断注解是否为必须字段，然后做校验
 *
 */
public class ParamCheckUtil {

	private static final String TIP = "不能为空!";

	private static final Logger logger = LoggerFactory.getLogger(ParamCheckUtil.class);

	public static void isNull(Object obj) {
		Class<?> clazz = obj.getClass();

		List<Field> fieldlist = new ArrayList<Field>(Arrays.asList(clazz.getDeclaredFields()));

		fieldlist.addAll(new ArrayList<Field>(Arrays.asList(clazz.getSuperclass().getDeclaredFields())));

		fieldlist.forEach(f -> {
			ApiModelProperty anno = f.getAnnotation(ApiModelProperty.class);
			if (anno != null && anno.required()) {// 是否为必须字段
				Object value = null;
				try {
					f.setAccessible(true);
					value = f.get(obj);
				} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
					logger.debug("反射异常：" + e.getMessage());
				}
				Assert.notNull(value, anno.value() + TIP);
			}
		});
	}

	public static void main(String[] args) {
		OrderRequestVo vo = new OrderRequestVo();
		isNull(vo);
	}

}
