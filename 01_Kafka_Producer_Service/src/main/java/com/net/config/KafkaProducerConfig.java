package com.net.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
@Configuration
@RefreshScope
public class KafkaProducerConfig {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaProducerConfig.class);
	
	@Autowired
	private KafkaProducerProperties kafkaProducerProperties;
	
	@Bean
	public ProducerFactory<String ,Object> producerFactory(){
		Map<String,Object>configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProperties.getBootStrapServers());
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(configProps);
	}
	
		@Bean
		KafkaTemplate<String,Object>kafkaProducerTemplate(){
		KafkaTemplate<String,Object>kafkaTemplate = new KafkaTemplate<>(producerFactory());
		
		kafkaTemplate.setProducerListener(new ProducerListener<String,Object>(){
		@Override
		public void onSuccess(ProducerRecord<String,Object>producerRecord,RecordMetadata recordMetadata) {
			log.info("Ack ProducerListner message:{} offset:{}",
			producerRecord.value().toString(),recordMetadata.offset());
		}
		public void onError(ProducerRecord<String,Object>producerRecord, RecordMetadata recordMetadata,Exception exception) {
			ProducerListener.super.onError(producerRecord,recordMetadata,exception);
			log.error("Error from ProducerListner message:{} offset:{} ",producerRecord.value(),
					recordMetadata.offset());
			log.error("Error from ProductListner exception:{}",exception.getMessage());
		}
		});
		return kafkaTemplate;
	}
		
	}


