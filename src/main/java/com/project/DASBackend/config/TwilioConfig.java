package com.project.DASBackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
@Component
public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String trialNumber;

}

