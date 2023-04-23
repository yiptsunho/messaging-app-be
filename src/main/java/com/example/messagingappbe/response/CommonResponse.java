package com.example.messagingappbe.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CommonResponse {
    private Integer code;
    private String status;
    private String message;

    public static CommonResponse success(String message) {
        return CommonResponse.builder()
                .code(200)
                .status("success")
                .message(message)
                .build();
    }
    public static CommonResponse fail(Integer code, String message) {
        return CommonResponse.builder()
                .code(code)
                .status("fail")
                .message(message)
                .build();
    }
}
