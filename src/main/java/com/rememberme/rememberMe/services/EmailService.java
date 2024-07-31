package com.rememberme.rememberMe.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public EmailService(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    public void sendEmail(String to, String subject, String text) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource("peruccii2917@hotmail.com")
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message().withSubject(new Content(subject)).withBody(new Body()
                        .withText(new Content(text))));
        try {
            this.amazonSimpleEmailService.sendEmail(request);
        } catch (AmazonServiceException ex) {
            throw new AmazonServiceException("Failed to send email", ex);
        }
    }
}
