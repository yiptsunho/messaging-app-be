package com.example.messagingappbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MessageDto {

    private Long id;
    private String type;
    private String content;
    private LocalDateTime dateTime;
    private Long senderId;
    private Long receiverId;
    private Long groupId;

    public MessageDto(Long id, String type, String content, LocalDateTime dateTime, Long senderId, Long receiverId, Long groupId) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.dateTime = dateTime;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.groupId = groupId;
    }

    public MessageDto(Long id, String type, String content, LocalDateTime dateTime, Long senderId, Long receiverId) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.dateTime = dateTime;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
