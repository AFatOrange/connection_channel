package com.connection.channel.main.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connection.channel.main.dto.MeterDTO;
import com.connection.channel.main.mysql.mapper.MeterMapper;

@Service
public class MeterService {
	
	@Autowired
	MeterMapper meterMapper;
	@Transactional(transactionManager = "mysqlTransactionManager")
	
	public List<MeterDTO> queryMeter() {
		List<MeterDTO> meterList = meterMapper.queryMeter();
		return meterList;
	}

}
