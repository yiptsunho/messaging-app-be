package com.example.messagingappbe.dto;

import com.example.messagingappbe.model.Group;
import com.example.messagingappbe.model.Message;
import com.example.messagingappbe.model.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RecentChatDto {
    private Long id;
    private String type;
    private String content;
    private LocalDateTime dateTime;
    private Long senderId;
    private Long receiverId;
    private Long groupId;
    private Boolean isGroup;
    private String name;
    private byte[] avatar;

    public RecentChatDto(Long id, String type, String content, LocalDateTime dateTime, Long senderId, Long receiverId, Long groupId, Boolean isGroup, String name, byte[] avatar) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.dateTime = dateTime;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.groupId = groupId;
        this.isGroup = isGroup;
        this.name = name;
        this.avatar = avatar;
    }
}
