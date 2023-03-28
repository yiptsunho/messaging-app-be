package com.example.messagingappbe.controller;

import com.example.messagingappbe.model.Message;
import com.example.messagingappbe.response.CommonResponse;
import com.example.messagingappbe.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class MessageController {
    private MessageService messageService;

    @Autowired
    public void MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/message") //app/message
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message) {
        return messageService.receivePublicMessage(message);
    }

    @MessageMapping("/private-message")

    public Message receivePrivateMessage(@Payload Message message) {
        return messageService.receivePrivateMessage(message);
    }
}
