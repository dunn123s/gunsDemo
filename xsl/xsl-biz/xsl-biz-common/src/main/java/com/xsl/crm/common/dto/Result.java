package com.xsl.crm.common.dto;

import lombok.Data;

@Data
public class Result<T> {
	public static final int SUCCESS = 200;
	public static final int ERROR = 500;
	public static final String SUCCESS_MESSAGE = "请求成功";
    public static final String ERROR_MESSAGE = "请求失败";
	private Integer code;
	private String message;
	private T data;

	public Result() {
	}

	private Result(Integer rcode, String msg) {
		this.code = rcode;
		this.message = msg;
	}


	public Boolean getSuccess() {
		return code == SUCCESS;
	}

	public static <T> Result<T> success(T target) {
		Result<T> result = new Result<T>(SUCCESS, SUCCESS_MESSAGE);
		result.setData(target);
		return result;
	}

	public static <T> Result<T> success() {
		return success(null);
	}

	public static <T> Result<T> error(String message) {
		Result<T> result = new Result<T>(ERROR, message);
		return result;
	}
	public static <T> Result<T> error(String code,String msg) {
		Result<T> result = new Result<T>(Integer.parseInt(code), msg);
		return result;
	}

	public static <T> Result<T> error(Exception e) {
		return error(e.getMessage());
	}
	
	public static <T> Result<T> result(Result<?> result) {
		return new Result<T>(result.getCode(), result.getMessage());
	}


}
