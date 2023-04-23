package com.example.messagingappbe.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private Integer code;
    private String status;
    private String message;
    private String accessToken;
    private String refreshToken;
    private Long userId;
    private String displayName;
    public AuthenticationResponse fail(Integer code, String message) {
        return AuthenticationResponse.builder()
                .code(code)
                .status("fail")
                .message(message)
                .build();
    }
}
