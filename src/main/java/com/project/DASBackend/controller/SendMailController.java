package com.project.DASBackend.controller;

import com.project.DASBackend.dto.MailStructure;
import com.project.DASBackend.service.impl.MailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/mail")
public class SendMailController {
    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping
    public void sendMail(@RequestBody MailStructure mailStructure) {
        mailSenderService.sendEmail(mailStructure);
    }
}
