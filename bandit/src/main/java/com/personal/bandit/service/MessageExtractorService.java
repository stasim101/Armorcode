package com.personal.bandit.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.personal.bandit.model.Bandit;
import com.personal.bandit.repository.BanditRepository;

public class MessageExtractorService {

	static String tenant;

	@Autowired
	private BanditRepository banditRepository;

	public void messageExtractor(String message) {

		if (message.contains("Organization:"))
			tenant = StringUtils.substringAfter(message, "Organization:").trim();

		else {

			Bandit bandit = new Bandit();

			String key = "More Info:";
			bandit.setMoreInfo(StringUtils.substringAfter(message, key).trim());
			message = StringUtils.substringBefore(message, key);

			key = "Location:";
			bandit.setLocation(StringUtils.substringAfter(message, key).trim());
			message = StringUtils.substringBefore(message, key);

			key = "Confidence:";
			bandit.setConfidence(StringUtils.substringAfter(message, key).trim());
			message = StringUtils.substringBefore(message, key);

			key = "Severity:";
			bandit.setSeverity(StringUtils.substringAfter(message, key).trim());
			message = StringUtils.substringBefore(message, key);

			key = "Issue:";
			bandit.setIssue(StringUtils.substringAfter(message, key).trim());

			bandit.setTenantId(tenant);

			banditRepository.save(bandit);
		}

	}

	public static void main(String[] args) {

		String message = "Issue: [B404:blacklist] Consider possible security implications associated with subprocess module.  "
				+ " Severity: Low" + " Confidence: High " + " Location: fportaintier\\vulpy\\bad\\brute.py:3"
				+ " More Info: https://bandit.readthedocs.io/en/latest/blacklists/blacklist_imports.html"
				+ "#b404-import-subprocess2  " + "3       import subprocess" + "4       import sys";

		
	}
}
