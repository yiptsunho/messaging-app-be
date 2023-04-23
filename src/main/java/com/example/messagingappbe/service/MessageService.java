package com.example.messagingappbe.service;

import com.example.messagingappbe.repository.MessageRepository;
import com.example.messagingappbe.request.CommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public static String getAllMessage(Long userId) {
        return null;
    }

    public static String updateMessage(CommonRequest commonRequest) {
        return null;
    }

    public static String deleteMessage(Long messageId) {
        return null;
    }
}
