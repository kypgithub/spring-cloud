package com.abugong.cloud.mysql.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.abugong.cloud.mysql.dao.PemissionDao;
import com.abugong.cloud.mysql.entity.Pemission;
import com.abugong.cloud.mysql.service.PemissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service
public class PemissionServiceImpl extends ServiceImpl<PemissionDao, Pemission> implements PemissionService {

	@Override
	public List<Pemission> getPemissionList(Integer id) {
		// TODO Auto-generated method stub
		return baseMapper.getPemissionList(id);
	}

	
}
