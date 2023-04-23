package com.example.messagingappbe.service;

import com.example.messagingappbe.model.Message;
import com.example.messagingappbe.repository.GroupRepository;
import com.example.messagingappbe.repository.MessageRepository;
import com.example.messagingappbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SocketService {

    private static SimpMessagingTemplate simpMessagingTemplate;
    private static MessageRepository messageRepository;
    private static UserRepository userRepository;
    private static GroupRepository groupRepository;

    @Autowired
    public SocketService(SimpMessagingTemplate simpMessagingTemplate, MessageRepository messageRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public static Message sendMessage(Message message) {
        Long receiverId = message.getReceiverId();
        Long senderId = message.getSenderId();
        Long groupId = message.getGroupId();
        Boolean isGroup = message.getIsGroup();

        if (senderId == null) {
            throw new IllegalStateException("senderId must not be null");
        }
        if (isGroup == null) {
            throw new IllegalStateException("isGroup must not be null");
        }
        if (!isGroup && receiverId == null) {
            throw new IllegalStateException("receiverId must not be null in a private message");
        }
        if (isGroup && groupId == null) {
            throw new IllegalStateException("groupId must not be null in a group message");
        }
        if (userRepository.existsById(senderId) == false) {
            throw new IllegalStateException("senderId does not exist in the system");
        }
        if (!isGroup && userRepository.existsById(receiverId) == false) {
            throw new IllegalStateException("receiverId does not exist in the system");
        }
        if (isGroup && groupRepository.existsById(groupId) == false) {
            throw new IllegalStateException("groupId does not exist in the system");
        }
        if (message.getType() == null || message.getContent() == null || message.getDateTime() == null) {
            throw new IllegalStateException("type, content or datetime must not be null");
        }
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverId().toString(), "", message); // /user/David/private
        messageRepository.save(message);
        return message;
    }
}
