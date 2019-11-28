package com.abugong.cloud.mysql.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.abugong.cloud.mysql.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

@Mapper
public interface UserDao extends BaseMapper<User>{
	User findUser(@Param("id")int id);
}
