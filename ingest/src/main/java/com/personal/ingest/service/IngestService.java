package com.personal.ingest.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ingest.exception.ToolException;
import com.personal.ingest.model.ScanTool;
import com.personal.ingest.repository.ScanToolRepository;

@Service
public class IngestService {

	@Autowired
	private ScanToolRepository scanToolRepository;

	@Autowired
	private ChunkCreationService chunkCreationService;

	@Autowired
	private KafkaProducerService kafkaProducerService;

	private static final Logger log = LoggerFactory.getLogger(IngestService.class);

	public List<String> parse(MultipartFile file,String token, String tool) throws ToolException, IOException {

		Optional<ScanTool> obj = scanToolRepository.findByToolName(tool);
		ScanTool scanTool = obj.orElseThrow(() -> new ToolException("Tool not found :" + tool));
		String delimeter = scanTool.getTopicDetails().getDelimeter();

		// Parse data into chunks
		List<String> list = chunkCreationService.createFileChunks(file, delimeter);
	
		//Send token as a first message in queue
		kafkaProducerService.sendMessage(tool, "TOKEN - "+token);
		
		// Redirect chunks to tool specific tool topic
		for (String message : list)
			kafkaProducerService.sendMessage(tool, message);
		return list;
	}

}
