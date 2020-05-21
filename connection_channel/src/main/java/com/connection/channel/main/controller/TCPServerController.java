package com.connection.channel.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connection.channel.main.dto.TCPServerDTO;
import com.connection.channel.main.sqlite.service.TCPServerService;

@RestController
public class TCPServerController {
	
	@Autowired
	private TCPServerService tcpServerService;
	
	@RequestMapping("/queryTCPServer")
	public List<TCPServerDTO> queryTCPServer() {
		
		return tcpServerService.queryTCPServer();
		
	}

}
