package com.project.DASBackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MailStructure {

    private String toEmail;

    private String subject;

    private String name;

    private String messageBody;

    private String senderName;
}
