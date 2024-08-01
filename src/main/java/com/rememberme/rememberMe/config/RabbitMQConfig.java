package com.rememberme.rememberMe.config;

import com.rememberme.rememberMe.config.constants.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    private static final String NAME_EXCHANGE = "amq.direct"; // TODO: Change to email-request-exchange
    private AmqpAdmin amqpAdmin;

    public RabbitMQConfig(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding relation(Queue queue, DirectExchange direct) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, direct.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void add() {
        Queue stockQueue =  this.queue(RabbitMQConstants.STOCK_QUEUE);
        Queue stockEmail =  this.queue(RabbitMQConstants.STOCK_EMAIL);

        DirectExchange direct = this.directExchange();

        Binding ligationStock = this.relation(stockQueue, direct);
        Binding ligationEmail = this.relation(stockQueue, direct);

        // Creating the queues
        this.amqpAdmin.declareQueue(stockQueue);
        this.amqpAdmin.declareQueue(stockEmail);

        this.amqpAdmin.declareExchange(direct);

        this.amqpAdmin.declareBinding(ligationStock);
        this.amqpAdmin.declareBinding(ligationEmail);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}



