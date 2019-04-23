package com.ty.excel;

import java.util.Map;

/**
 * 使用Excel生成Javamodel实体类，可以带有注解，以及注释，该对象为Excel实体类
 *
 * @ClassName ExcelData
 * @Author tianye
 * @Date 2019/4/23 16:07
 * @Version 1.0
 */
public class ExcelData {

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 注解名称
     */
    private String annotationName;

    /**
     * 注解属性名称 --> 属性值
     */
    private Map<String,String> annotationFields;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnotationName() {
        return annotationName;
    }

    public void setAnnotationName(String annotationName) {
        this.annotationName = annotationName;
    }

    public Map<String, String> getAnnotationFields() {
        return annotationFields;
    }

    public void setAnnotationFields(Map<String, String> annotationFields) {
        this.annotationFields = annotationFields;
    }
}
