package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.MailStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(MailStructure mailStructure) {
        String body = String.format("Kính thưa quý ông/bà %s,\n\n%s\n\nRất hân hạnh,\n%s", mailStructure.getName(), mailStructure.getMessageBody(), mailStructure.getSenderName());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailStructure.getToEmail());
        message.setSubject(mailStructure.getSubject());
        message.setText(body);
        javaMailSender.send(message);
    }
}
