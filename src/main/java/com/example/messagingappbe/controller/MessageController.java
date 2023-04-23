package com.example.messagingappbe.controller;

import com.example.messagingappbe.request.CommonRequest;
import com.example.messagingappbe.response.CommonResponse;
import com.example.messagingappbe.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public String getAllMessage(@RequestParam Long userId) {
        return MessageService.getAllMessage(userId);
    }

    @PutMapping()
    public String updateMessage(@RequestBody CommonRequest commonRequest) {
        return MessageService.updateMessage(commonRequest);
    }

    @DeleteMapping()
    public String deleteMessage(@RequestParam Long messageId) {
        return MessageService.deleteMessage(messageId);
    }
}
