package com.abugong.cloud.mysql.service.impl;

import org.springframework.stereotype.Service;

import com.abugong.cloud.mysql.dao.UserDao;
import com.abugong.cloud.mysql.entity.User;
import com.abugong.cloud.mysql.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User>implements UserService{
   
}
