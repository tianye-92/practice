package com.ty.quartz.entity;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;

public class Page<T> extends HashMap<String, Object> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int nowPage;// 当前页
	private static int rowNum;// 每页显示行数

	public static void builder(String currPage, String row) {
		nowPage = StringUtils.isBlank(currPage) ? 1 : (Integer.valueOf(currPage) <= 0 ? 1 : Integer.valueOf(currPage));
		rowNum = StringUtils.isBlank(row) ? 1 : (Integer.valueOf(row) <= 0 ? 1 : Integer.valueOf(row));
		PageHelper.startPage(nowPage, rowNum, true);
	}

	public Page(PageInfo<T> pageInfo) {
		this.put("pageInfo", pageInfo);
	}
}
