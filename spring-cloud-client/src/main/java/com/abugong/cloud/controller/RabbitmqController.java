package com.abugong.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abugong.cloud.mq.MsgProducer;

@RestController
public class RabbitmqController {
	
	@Autowired
	MsgProducer msgProducer;
	
	@GetMapping("/sendRabbitmqMsg")
	public void send() throws InterruptedException { 
		msgProducer.send(); 
	}
}
