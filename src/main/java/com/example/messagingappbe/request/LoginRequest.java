package com.example.messagingappbe.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotNull(message = "name must not be null")
    private String username;
    @NotNull(message = "password must not be null")
    private String password;
}
