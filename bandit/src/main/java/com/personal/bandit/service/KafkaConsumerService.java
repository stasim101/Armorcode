package com.personal.bandit.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

	@Autowired
	private MessageExtractorService messageExtractorService;

	@KafkaListener(topics = "bandit", groupId = "group_id")
	public String consume(String message) throws IOException {
		messageExtractorService.saveExtract(message);
		return message;
	}

}
