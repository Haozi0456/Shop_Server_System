package com.zwh.shop.controller;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zwh.shop.entity.User;
import com.zwh.shop.service.UserService;
import com.zwh.system.common.MessageCode;
import com.zwh.system.common.Result;

@RestController
@RequestMapping("/system/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public Result addUser(User user) {
		int row = userService.insertSelective(user);
		if(row > 0) {
			return new Result(MessageCode.SUCCESS, "添加成功!");
		}
		return new Result(MessageCode.ERROR, "添加失败!");
	}
}
