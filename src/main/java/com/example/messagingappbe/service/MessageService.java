package com.example.messagingappbe.service;

import com.example.messagingappbe.model.Message;
import com.example.messagingappbe.model.User;
import com.example.messagingappbe.repository.MessageRepository;
import com.example.messagingappbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class MessageService {

    private SimpMessagingTemplate simpMessagingTemplate;
    private MessageRepository messageRepository;
    private UserRepository userRepository;

    @Autowired
    public void MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Message receivePublicMessage(Message message) {
        return message;
    }

    public Message receivePrivateMessage(Message message) {
        if (Objects.nonNull(message.getReceiverId())) {
            throw new IllegalStateException("receiverId must not be null");
        }
        boolean receiverExists = userRepository.existsById(message.getReceiverId());
        if (!receiverExists) {
            throw new IllegalStateException("receiver with receiverId " + message.getReceiverId() + " does not exist");
        }
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverId().toString(), "/private", message); // /user/${USERNAME}/private
        return message;
    }
}
