package com.zwh.shop.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zwh.shop.entity.User;
import com.zwh.shop.service.UserService;
import com.zwh.system.common.MessageCode;
import com.zwh.system.common.Result;
import com.zwh.system.utils.Md5SaltUtil;

@RestController
@RequestMapping("/system/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public Result register(User user) {
		try {
			if (user != null) {
				//对密码进行加盐加密
				String encryptedPwd = Md5SaltUtil.getEncryptedPwd(user.getPassword());
				user.setPassword(encryptedPwd);
				int row = userService.insertSelective(user);
				if (row > 0) {
					return new Result(MessageCode.SUCCESS, "添加成功");
				}
				return new Result(MessageCode.ERROR, "添加失败");
			} else {
				return Result.PARAM_ERROR;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return Result.ERROR;
	}

	@PostMapping("/login")
	public Result login(String account, String pwd) {
		try {
			User user = userService.selectByAccount(account);
			if (user != null) {
				String pwdInDb = user.getPassword();
				//对密码进行解密验证
				boolean flag = Md5SaltUtil.validPassword(pwd, pwdInDb);
				if(flag) {
					return new Result(MessageCode.SUCCESS, "登录成功", user);
				}else {
					return new Result(MessageCode.ERROR, "密码错误");
				}
			} else {
				return new Result(MessageCode.ERROR, "该用户不存在");
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return Result.ERROR;
	}
	
	
	@PostMapping("/resetPwd")
	public Result resetPwd(String account, String pwd) {
		try {
			User user = userService.selectByAccount(account);
			if (user != null) {
				//对密码进行加盐加密
				String encryptedPwd = Md5SaltUtil.getEncryptedPwd(pwd);
				user.setPassword(encryptedPwd);
				int row = userService.updateByPrimaryKeySelective(user);
				if(row > 0) {
					return new Result(MessageCode.SUCCESS, "修改成功");
				}else {
					return new Result(MessageCode.ERROR, "修改失败");
				}
			} else {
				return new Result(MessageCode.ERROR, "该用户不存在");
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return Result.ERROR;
	}
}
