package com.personal.ingest.exception;

public class ToolException extends Exception {

	private String message;

	public ToolException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ToolException occured : "+message;
	}
}
