package com.connection.channel.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.connection.channel.main.start.Start;

@Component
public class ResourceInit implements ApplicationRunner {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Start start;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		log.info("___________________开始___________________");
		start.init();
		
	}
	
	

}
