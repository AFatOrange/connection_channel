package com.connection.channel.main.sqlite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.connection.channel.main.dto.TCPServerDTO;

@Mapper
public interface TCPServerMapper {

	@Select("select * from serialServer")
	List<TCPServerDTO> queryTCPServer ();
	
}
