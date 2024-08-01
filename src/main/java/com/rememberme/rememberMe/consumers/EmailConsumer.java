package com.rememberme.rememberMe.consumers;

import com.rememberme.rememberMe.config.constants.RabbitMQConstants;
import com.rememberme.rememberMe.dtos.EmailSenderDTO;
import com.rememberme.rememberMe.services.EmailSender;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailSender emailSender;

    public EmailConsumer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @RabbitListener(queues = RabbitMQConstants.STOCK_QUEUE)
    public void listen(Message<EmailSenderDTO>  payload) {
        this.emailSender.sendMail(payload.getPayload().to());
        System.out.println("Email sent to " + payload.getPayload());
    }

}
