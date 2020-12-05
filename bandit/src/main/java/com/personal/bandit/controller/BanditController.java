package com.personal.bandit.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.bandit.model.Bandit;
import com.personal.bandit.service.BanditService;
import com.personal.bandit.service.KafkaConsumerService;

@RestController
public class BanditController {

	@Autowired
	private KafkaConsumerService kafkaConsumerService;
	@Autowired
	private BanditService banditService;

	@GetMapping("/readTopic")
	public String readTopic(@RequestParam("message") String message) throws IOException {
		return kafkaConsumerService.consume(message);
	}

	
}
