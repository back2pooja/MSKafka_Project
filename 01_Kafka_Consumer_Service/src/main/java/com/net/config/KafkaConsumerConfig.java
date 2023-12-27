package com.net.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.net.constant.AppConstant;

@Configuration
@EnableKafka
@ConfigurationProperties(prefix = "kafka.status.producer")
public class KafkaConsumerConfig {
	
	//private static final Logger log = LoggerFactory.getLogger(KafkaConsumerConfig.class.getName());
	
	@Bean
	public ConsumerFactory<String,Object>consumerFactory(){
		Map<String,Object>config = new HashMap<>();
		
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConstant.HOST_URL);
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConstant.TOPIC_NAME);
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConstant.GROUP_Id);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),new JsonDeserializer<Object>());

	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,Object>kafkaListener(){
		ConcurrentKafkaListenerContainerFactory<String,Object>factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
