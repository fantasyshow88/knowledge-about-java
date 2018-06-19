package com.roocon.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.roocon.incre.Bean;

public interface AppMapper {
	
	List<Map<String, Object>> executeQuery(@Param("sql")String sql);

	String executeQueryDname(@Param("sql")String sql);

	int insert(Bean b);

}
