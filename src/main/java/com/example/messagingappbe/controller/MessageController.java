package com.example.messagingappbe.controller;

import com.example.messagingappbe.model.Message;
import com.example.messagingappbe.response.CommonResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public CommonResponse greet(Message message) {
        return CommonResponse.builder()
                .code(200)
                .status("success")
                .message("message received" + message.getContent())
                .build();
    }
}
