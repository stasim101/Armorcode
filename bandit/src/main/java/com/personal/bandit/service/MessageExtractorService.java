package com.personal.bandit.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.personal.bandit.model.Bandit;

public class MessageExtractorService {

	public String messageExtractor(String message) {

		if (message.contains("Organization")) {
			Pattern pattern = Pattern.compile(
					"(\\p{Alnum}[\\p{Alnum}\\s/]+?)\\s?(:|\\.+)\\s?(\\p{Alnum}[\\p{Alnum}\\s/]+?)(?=($|\\()|(\\s\\())",
					Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(message);
			while (matcher.find()) {
				System.out.println(matcher.group(1) + "-->" + matcher.group(3));
			}
		}

		return null;
	}

	public static void main(String[] args) {

		MessageExtractorService ins = new MessageExtractorService();
		String s = "Issue: [B404:blacklist] Consider possible security implications associated with subprocess module.  "
				+ " Severity: Low"
				+ " Confidence: High "
				+ " Location: fportaintier\\vulpy\\bad\\brute.py:3"
				+ " More Info: https://bandit.readthedocs.io/en/latest/blacklists/blacklist_imports.html"
				+ "#b404-import-subprocess2  "
				+ "3       import subprocess"
				+ "4       import sys";
		
		Bandit bandit = new Bandit();

		String key = "More Info:";
		bandit.setMoreInfo(StringUtils.substringAfter(s, key));
		s = StringUtils.substringBefore(s, key);
		
		key = "Location:";
		bandit.setLocation(StringUtils.substringAfter(s, key));
		s = StringUtils.substringBefore(s, key);
		
		key = "Confidence:";
		bandit.setConfidence(StringUtils.substringAfter(s, key));
		s = StringUtils.substringBefore(s, key);

		
		key = "Severity:";
		bandit.setSeverity(StringUtils.substringAfter(s, key));
		s = StringUtils.substringBefore(s, key);

		key = "Issue:";
		bandit.setIssue(StringUtils.substringAfter(s, key));
		
		
		//ins.messageExtractor(s);	
		

	}
}
