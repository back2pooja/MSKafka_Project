package com.net.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerProperties {
	
	private String bootStrapServers;
	private String topicName;
	public String getBootStrapServers() {
		return bootStrapServers;
	}
	public void setBootStrapServers(String bootStrapServers) {
		this.bootStrapServers = bootStrapServers;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
	
	

}
