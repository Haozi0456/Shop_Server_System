package com.zwh.system.utils;

import com.zwh.system.common.Result;

/**
 * @author ZhaoHao
 * 业务逻辑错误异常处理
 */
public class BusinessException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private Integer code;  //错误码
 
	public BusinessException() {}
	
	public BusinessException(Result resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}
	
	public Integer getCode() {
		return code;
	}
 
	public void setCode(Integer code) {
		this.code = code;
	}
}
