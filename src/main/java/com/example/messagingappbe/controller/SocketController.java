package com.example.messagingappbe.controller;

import com.example.messagingappbe.model.Message;
import com.example.messagingappbe.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {
    private SocketService socketService;
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public void SocketController(SocketService socketService, SimpMessagingTemplate simpMessagingTemplate) {
        this.socketService = socketService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

//    @MessageMapping("/message") //app/message
//    @SendTo("/chatroom/public")
////    @SendTo("/group/{id}")
//    public Message receivePublicMessage(@Payload Message message) {
////        return messageService.receivePublicMessage(message);
//        return message;
//    }

    @MessageMapping("/message")
    public Message receivePrivateMessage(@Payload Message message) {
//        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getReceiver().getId()), "", message); // /user/David/private
//        return message;
        return SocketService.sendMessage(message);
    }


}
