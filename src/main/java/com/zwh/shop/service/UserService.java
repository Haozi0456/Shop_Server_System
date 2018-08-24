package com.zwh.shop.service;


import com.zwh.shop.entity.User;


public interface UserService {
	
	int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

}
