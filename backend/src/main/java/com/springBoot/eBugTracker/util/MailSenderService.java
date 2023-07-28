package com.springBoot.eBugTracker.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String toEmail,
                         String subject,
                         String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vrushant28@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);

        System.out.println("Mail Sent Successfully.... ");
    }
}
