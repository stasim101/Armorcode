package com.personal.ingest.contoller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ingest.model.ScanTool;
import com.personal.ingest.model.TopicDetails;
import com.personal.ingest.repository.ScanToolRepository;
import com.personal.ingest.repository.TopicDetailsRepository;
import com.personal.ingest.service.ChunkCreationService;
import com.personal.ingest.service.IngestService;
import com.personal.ingest.service.KafkaProducerService;

@RestController
public class IngestController {

	@Autowired
	private Environment env;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private IngestService ingestService;
	@Autowired
	private ScanToolRepository scanToolRepository;
	@Autowired
	private TopicDetailsRepository topicDetailsRepository;
	@Autowired
	private ChunkCreationService chunkCreationService;
	@Autowired
	private KafkaProducerService kafkaProducerService;

	@SuppressWarnings("rawtypes")
	@PostMapping("/uploadFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseEntity ingestData(@RequestParam("file") MultipartFile file, @RequestParam("tool") String tool)
			throws Exception {

		return null;
	}

	@GetMapping("/addTopic")
	public String addNewTopic(@RequestParam("topic") String topicName, @RequestParam("url") String url,
			@RequestParam("delimeter") String delimeter) {
		TopicDetails topicDetails = topicDetailsRepository.save(new TopicDetails(topicName, delimeter));
		ScanTool scanTool = scanToolRepository.save(new ScanTool(topicName, topicDetails, url));
		return scanTool.toString();
	}

	@PostMapping("/validate")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void inputData(HttpServletResponse response, @RequestParam("file") MultipartFile file,
			@RequestParam("token") String token, @RequestParam("tool") String tool)
			throws IOException, ClassNotFoundException, URISyntaxException {

		// Validate the tool and token details and get the response
		URI uri = new URI(env.getProperty("validate_token") + token);
		String organization = restTemplate.getForObject(uri, String.class);
		
		// Get the corresponding token from application.properties
		String TOPIC = env.getProperty(tool);
		String delimeter = env.getProperty(tool + "-del");

		List<String> content = chunkCreationService.createFileChunks(file, delimeter);
		content.add(0, "Organization: " + organization);

		for (String message : content) {
			kafkaProducerService.sendMessage(TOPIC, message);
			
			}
		}

	// Receive token validation response
	@SuppressWarnings("rawtypes")
	@GetMapping("/orgstatus")
	public ResponseEntity validateInputToken(@RequestParam("org") String org, @RequestParam("token") String token)
			throws IOException {

//		return new ResponseEntity<String>("Invalid token entered by the user", new HttpHeaders(), HttpStatus.CREATED);

		return null;
	}

// Execute the required method

}