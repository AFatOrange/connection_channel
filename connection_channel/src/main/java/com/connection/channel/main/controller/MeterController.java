package com.connection.channel.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connection.channel.main.dto.MeterDTO;
import com.connection.channel.main.mysql.service.MeterService;


@RestController
public class MeterController {
	
	@Autowired
	private MeterService meterService;
	
	@RequestMapping("/queryMeter")
	public List<MeterDTO> queryMeter() {
		
		return meterService.queryMeter();
		
	}
	

}
