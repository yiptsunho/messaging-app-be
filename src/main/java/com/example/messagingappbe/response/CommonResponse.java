package com.example.messagingappbe.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.java.ObjectJavaType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CommonResponse {
    private Integer code;
    private String status;
    private String message;
    private Object data;

    public static CommonResponse success(String message) {
        return CommonResponse.builder()
                .code(200)
                .status("success")
                .message(message)
                .build();
    }

    public static CommonResponse success(Object data) {
        return CommonResponse.builder()
                .code(200)
                .status("success")
                .data(data)
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
