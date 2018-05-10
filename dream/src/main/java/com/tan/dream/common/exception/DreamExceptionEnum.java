package com.tan.dream.common.exception;

/**
 * dream异常枚举
 *
 * @author tanleijin
 * @Date 2018/2/8 下午10:33
 */
public enum DreamExceptionEnum implements ServiceExceptionEnum{

	/**
	 * 其他
	 */
	WRITE_ERROR(500,"渲染界面错误"),
	GET_CONFIG_ERROR(500,"获取配置文件失败"),
	SAVE_CONFIG_ERROR(500,"保存配置文件失败"),

	/**
	 * 文件上传
	 */
	FILE_READING_ERROR(400,"FILE_READING_ERROR!"),
	FILE_NOT_FOUND(400,"FILE_NOT_FOUND!"),

	/**
	 * 错误的请求
	 */
	REQUEST_NULL(400, "请求有错误"),
	SERVER_ERROR(500, "服务器异常");

	DreamExceptionEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private Integer code;

	private String message;

	@Override
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
