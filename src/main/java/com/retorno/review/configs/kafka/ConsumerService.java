package com.retorno.review.configs.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "meu-topico", groupId = "my-group")
    public void consume(String message) {
        System.out.println(message);
    }
}
