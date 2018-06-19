package com.hy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hy.db.DataSource;
import com.hy.db.DbContextHolder;

public interface AppMapper {
	
	@DataSource(value = DbContextHolder.MASTER)
	List<Map<String, Object>> executeQuery(@Param("sql")String sql);
	
	@DataSource(value = DbContextHolder.SLAVE)
	List<Map<String, Object>> executeQuery2(@Param("sql")String sql);

}
