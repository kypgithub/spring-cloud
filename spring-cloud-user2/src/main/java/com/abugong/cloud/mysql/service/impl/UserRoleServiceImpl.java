package com.abugong.cloud.mysql.service.impl;

import org.springframework.stereotype.Service;

import com.abugong.cloud.mysql.dao.UserRoleDao;
import com.abugong.cloud.mysql.entity.UserRole;
import com.abugong.cloud.mysql.service.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService{

}
