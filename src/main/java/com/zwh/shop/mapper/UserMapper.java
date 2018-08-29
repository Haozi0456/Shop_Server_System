package com.zwh.shop.mapper;

import org.apache.ibatis.annotations.Param;

import com.zwh.shop.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User login(@Param("account")String account, @Param("pwd")String pwd);
    
    User selectByAccount(String account);
}