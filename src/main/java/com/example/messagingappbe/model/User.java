package com.example.messagingappbe.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Generated
    private Long id;
    private String username;
    private String emailAddress;
    private String password;
    // avatar
}
