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
    public CommonResponse getRecentChat(@RequestParam Long id) {
        return MessageService.getRecentChat(id);
    }

    @GetMapping("/private")
    public CommonResponse getPrivateMessage(@RequestParam Long senderId, Long receiverId) {
        return MessageService.getPrivateMessage(senderId, receiverId);
    }

    @GetMapping("/group")
    public CommonResponse getGroupMessage(@RequestParam Long requesterId, Long groupId) {
        return MessageService.getGroupMessage(requesterId, groupId);
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
