package com.abugong.cloud.mysql.service.impl;

import org.springframework.stereotype.Service;

import com.abugong.cloud.mysql.dao.RoleDao;
import com.abugong.cloud.mysql.entity.Role;
import com.abugong.cloud.mysql.service.RoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService{

}
