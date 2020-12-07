package com.personal.bandit.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

	private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

	@KafkaListener(topics = "bandit", groupId = "group_id")
	public String consume(String message) throws IOException {
		logger.info(String.format("Consumed message -> %s", message));
		return message;
	}
}
