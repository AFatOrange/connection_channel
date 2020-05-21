package com.connection.channel.main.sqlite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connection.channel.main.dto.TCPServerDTO;
import com.connection.channel.main.sqlite.mapper.TCPServerMapper;

@Service
public class TCPServerService {
	
	@Autowired
	TCPServerMapper tcpServerMapper;
	@Transactional(transactionManager = "sqliteTransactionManager")
	
	public List<TCPServerDTO> queryTCPServer (){
		return tcpServerMapper.queryTCPServer();
	}
	
	

}
