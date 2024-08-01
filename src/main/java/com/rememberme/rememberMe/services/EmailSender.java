package com.rememberme.rememberMe.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rememberme.rememberMe.dtos.EmailSenderDTO;
import com.rememberme.rememberMe.producers.EmailProducer;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class EmailSender {


    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;



    @Transactional
    public void sendMail(String toEmail) {
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            String body = "<html>"
                    + "<head>"
                    + "<style>"
                    + "  .email-container {"
                    + "    font-family: Arial, sans-serif;"
                    + "    line-height: 1.6;"
                    + "    color: #333333;"
                    + "  }"
                    + "  .email-header {"
                    + "    background-color: #f8f8f8;"
                    + "    padding: 10px;"
                    + "    border-bottom: 1px solid #dddddd;"
                    + "  }"
                    + "  .email-body {"
                    + "    padding: 20px;"
                    + "  }"
                    + "  .button {"
                    + "    display: inline-block;"
                    + "    padding: 10px 20px;"
                    + "    margin: 20px 0;"
                    + "    font-size: 16px;"
                    + "    color: #ffffff;"
                    + "    background-color: #007bff;"
                    + "    text-decoration: none;"
                    + "    border-radius: 5px;"
                    + "  }"
                    + "  .button:hover {"
                    + "    background-color: #0056b3;"
                    + "  }"
                    + "</style>"
                    + "</head>"
                    + "<body class=\"email-container\">"
                    + "<div class=\"email-header\">"
                    + "<h1>Confirm Your Email Address</h1>"
                    + "</div>"
                    + "<div class=\"email-body\">"
                    + "<p>Thank you for registering. Please click the button below to verify your email address:</p>"
                    + "<a href=\"http://yourdomain.com/verify?email=" + toEmail + "\" class=\"button\">Verify my address</a>"
                    + "</div>"
                    + "</body>"
                    + "</html>";



            helper.setSubject("CONFIRM ACCOUNT");
            helper.setTo(toEmail);
            helper.setText(body, true);
            javaMailSender.send(message);

            System.out.println("Email sent successfully to: " + toEmail);
        } catch (MessagingException ex) {
            System.err.println("Failed to send email: " + ex.getMessage());
        }
    }

}
