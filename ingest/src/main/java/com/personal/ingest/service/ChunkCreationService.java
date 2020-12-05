package com.personal.ingest.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChunkCreationService {

	public List<String> createFileChunks(MultipartFile file, String delimeter) throws IOException {

		String line = "";
		StringBuilder chunk = new StringBuilder("");
		List<String> result = new ArrayList<>();
		InputStreamReader isReader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(isReader);

		while ((line = br.readLine()) != null) {
			if (line.startsWith(delimeter)) {
				result.add(chunk.toString());
				chunk = new StringBuilder(line);
			} else
				chunk.append(line);
		}
		return result;
	}

}
