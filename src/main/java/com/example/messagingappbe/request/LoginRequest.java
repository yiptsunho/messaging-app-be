package com.example.messagingappbe.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "emailAddress must not be null")
    private String emailAddress;
    @NotNull(message = "password must not be null")
    private String password;
}
