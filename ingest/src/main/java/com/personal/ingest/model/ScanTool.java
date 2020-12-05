package com.personal.ingest.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ScanTool  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "tool_name")
	private String toolName;
	@OneToOne(cascade = CascadeType.MERGE)
	private TopicDetails topicDetails;
	@Column(name = "service_url")
	private String serviceUrl;
	
	public ScanTool() {}
	
	public ScanTool(String toolName, TopicDetails topicDetails, String serviceUrl) {
		this.toolName = toolName;
		this.topicDetails = topicDetails;
		this.serviceUrl = serviceUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToolName() {		
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public TopicDetails getTopicDetails() {
		return topicDetails;
	}

	public void setTopicDetails(TopicDetails topicDetails) {
		this.topicDetails = topicDetails;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	@Override
	public String toString() {
		return " TOOLNAME - "+this.toolName+ ";   DELIMETER -  "+this.topicDetails.getDelimeter()+";   SERVICEURL -  "+this.serviceUrl;
	}
	
}
