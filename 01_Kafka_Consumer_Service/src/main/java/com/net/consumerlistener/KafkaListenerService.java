package com.net.consumerlistener;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.net.entity.Product;

@Component
public class KafkaListenerService {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaListenerService.class.getName());
	
	@KafkaListener(topics="orders",groupId="group_id",containerFactory="kafkaListener")
	public void consumerProduct(ConsumerRecord consumerRecord) {
		log.info(consumerRecord.topic(),consumerRecord.partition(),consumerRecord.offset(),
				consumerRecord.key(),consumerRecord.value());
		
		log.info("Product received from kafka broker= "+consumerRecord.value());
		try {
		Map<String,Object>passedValue = (LinkedHashMap<String,Object>)consumerRecord.value();
		
		Product product = new Product();
		for(Map.Entry<String,Object>mapTemp:passedValue.entrySet()) {
			if(mapTemp.getKey().equalsIgnoreCase("id")) {
				if(mapTemp.getValue()!=null)
					product.setId((String)mapTemp.getValue());
			}
			else if(mapTemp.getKey().equalsIgnoreCase("price")) {
				if(mapTemp.getValue()!=null) {
					Double price = (Double)mapTemp.getValue();
					product.setPrice(price);
				}
			}
		}
		log.info("Product received from kafka broker as Java Object = "+product);
		
	}
	catch(Exception e) {
		log.info(e.getMessage());
	}
	
	
	}	
	

}
