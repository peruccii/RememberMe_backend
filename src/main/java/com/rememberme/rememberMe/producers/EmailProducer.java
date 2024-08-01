package com.rememberme.rememberMe.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rememberme.rememberMe.config.constants.RabbitMQConstants;
import com.rememberme.rememberMe.dtos.EmailSenderDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmailProducer {


    private final AmqpTemplate amqpTemplate;

    public EmailProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendEmailProducer(EmailSenderDTO payload) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                RabbitMQConstants.STOCK_QUEUE,
                payload
        );
    }
}
