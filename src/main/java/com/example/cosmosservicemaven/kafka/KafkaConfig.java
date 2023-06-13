package com.example.cosmosservicemaven.kafka;

import com.example.cosmosservicemaven.serializer.Message;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Stack;

@Component
public class KafkaConfig {

    @Value("${topic.name}")
    private String topicName;

    @Value("${spring.kafka.producer.client-id}")
    private String clientId;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConfig.class);

    private static Stack<Message> messages;

    private final KafkaTemplate<String, Message> kafkaTemplate;

    KafkaConfig(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        messages = new Stack<>();
    }

    public Stack<Message> getMessages() {
        return messages;
    }

    public void send(Message message) {
        message.setTime(Instant.now());
        message.setUserName(clientId);
        kafkaTemplate.send(topicName, message);
    }

    @KafkaListener(topics = "basiceventhub", groupId = "cities_kafka")
    private void consumer(Message message) {
        LOGGER.info("Message: {}, time {}", message.getText(), message.getTime());
        messages.add(message);
    }

    @Bean
    NewTopic chatTopic() {

        return TopicBuilder
                .name(topicName)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
