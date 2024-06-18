package com.project.DASBackend.dto;

import lombok.*;

import java.io.Serializable;

/*
This class is required for storing the username and password we recieve from the client.
* */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String uid;
    private String password;
}

