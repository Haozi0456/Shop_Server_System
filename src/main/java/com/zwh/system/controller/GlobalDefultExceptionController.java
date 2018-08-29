package com.zwh.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwh.system.common.MessageCode;
import com.zwh.system.common.Result;
import com.zwh.system.utils.BusinessException;

/**
 * @author ZhaoHao
 *	全局错误处理
 */
@ControllerAdvice
public class GlobalDefultExceptionController {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result defultExcepitonHandler(HttpServletRequest request,Exception e) {
		e.printStackTrace();
		if(e instanceof BusinessException) {
//			Log.error(this.getClass(),"业务异常："+e.getMessage());
			BusinessException businessException = (BusinessException)e;
			return new Result(businessException.getCode(), businessException.getMessage());
		}
		//未知错误
		return new Result(MessageCode.SYSTEM_ERROR, "系统异常:"+e);
	}

	
}
