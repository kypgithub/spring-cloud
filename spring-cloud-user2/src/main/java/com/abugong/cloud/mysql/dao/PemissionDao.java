package com.abugong.cloud.mysql.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.abugong.cloud.mysql.entity.Pemission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
@Mapper
public interface PemissionDao extends BaseMapper<Pemission>{

	List<Pemission> getPemissionList(Integer id);

}
