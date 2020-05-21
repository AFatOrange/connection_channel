package com.connection.channel.main.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.connection.channel.main.dto.MeterDTO;

@Mapper
public interface MeterMapper {

	@Select("select * from meter")
	List<MeterDTO> queryMeter();
	
}
