package com.vatvruksh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail,String subject,String body)
    {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        //sender email
        simpleMailMessage.setFrom("vatvruksh.developers@gmail.com");

        //receiver email
        simpleMailMessage.setTo(toEmail);

        //email body
        simpleMailMessage.setText(body);

        simpleMailMessage.setSubject(subject);

        javaMailSender.send(simpleMailMessage);

        System.out.println("Message sent via email");

    }

}
