package com.ty.quartz.entity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 接口返回消息格式
 *
 * @param <T>
 */
@ApiModel(description = "返回JOSN对象")
public class ResultMessage<T> {

	@ApiModelProperty(value = "返回信息状态[true成功、false失败]")
	private final boolean status;

	@ApiModelProperty(value = "返回数据")
	private final T data;

	@ApiModelProperty(value = "返回信息描述")
	private final String message;

	private ResultMessage(ResultBuilder<T> message) {
		this.status = message.status;
		this.data = message.data;
		this.message = message.message;
	}

	public static <T> ResultMessage.ResultBuilder<T> builder() {
		return new ResultBuilder<>();
	}

	/**
	 *
	 * @param data    数据
	 * @param status  状态
	 * @param message 消息
	 * @return
	 */
	public static <T> ResultMessage<T> message(T data, boolean status, String message) {
		return ResultMessage.<T>builder().data(data).status(status).message(message).build();
	}

	/**
	 *
	 * @param status  状态
	 * @param message 消息
	 * @return
	 */
	public static <T> ResultMessage<T> message(boolean status, String message) {
		return ResultMessage.<T>builder().status(status).message(message).build();
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public boolean isStatus() {
		return status;
	}

	public T getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public static final class ResultBuilder<T> {

		private boolean status = false;

		private T data;

		private String message = "";

		private ResultBuilder() {

		}

		public ResultBuilder<T> message(String message) {
			this.message = message;
			return this;
		}

		public ResultBuilder<T> data(T data) {
			this.data = data;
			return this;
		}

		public ResultBuilder<T> status(boolean status) {
			this.status = status;
			return this;
		}

		public ResultMessage<T> build() {
			return new ResultMessage<>(this);
		}
	}
}
