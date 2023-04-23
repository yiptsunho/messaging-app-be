package com.example.messagingappbe.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @NotNull(message = "username must not be null")
    private String username;
    @NotNull(message = "password must not be null")
    private String password;
    private String phoneNumber;
    @NotNull(message = "emailAddress must not be null")
    private String emailAddress;
    private byte[] avatar;
}
