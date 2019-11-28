package com.abugong.cloud.mysql.service;

import java.util.List;

import com.abugong.cloud.mysql.entity.Pemission;
import com.baomidou.mybatisplus.service.IService;

public interface PemissionService extends IService<Pemission>{

	List<Pemission> getPemissionList(Integer id);

}
