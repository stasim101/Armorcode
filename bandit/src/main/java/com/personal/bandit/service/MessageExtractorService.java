package com.personal.bandit.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		ins.messageExtractor(s);		
	}
}
