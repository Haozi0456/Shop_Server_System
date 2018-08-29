package com.zwh.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwh.shop.entity.User;
import com.zwh.shop.mapper.UserMapper;
import com.zwh.shop.service.UserService;
import com.zwh.system.common.MessageCode;
import com.zwh.system.common.Result;
import com.zwh.system.utils.BusinessException;


@Service(value="userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	@Transactional
	public int insertSelective(User record) {
		int count = mapper.insertSelective(record);
		return count;
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public User login(String account, String pwd) {
		// TODO Auto-generated method stub
		return mapper.login(account, pwd);
	}

	@Override
	public User selectByAccount(String account) {
		// TODO Auto-generated method stub
		return mapper.selectByAccount(account);
	}

}
