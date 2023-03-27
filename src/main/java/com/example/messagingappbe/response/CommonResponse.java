package com.example.messagingappbe.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse {
    private Integer code;
    private String status;
    private String message;
}
