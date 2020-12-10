package com.personal.bandit.service;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.personal.bandit.repository.BanditRepository;

@Service
public class KafkaConsumerService {

	private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

	@Autowired
	private MessageExtractorService messageExtractorService;

	@Autowired
	private BanditRepository banditRepository;

	@KafkaListener(topics = "bandit", groupId = "group_id")
	public String consume(String message) throws IOException {
		messageExtractorService.saveExtract(message);
		return message;
	}

}
