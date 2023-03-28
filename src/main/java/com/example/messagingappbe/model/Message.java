package com.example.messagingappbe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Message {

    @Id
    @NotNull(message = "message id must not be null")
    private Long id;
    @NotNull(message = "message type must not be null")
    private String type;
    @NotNull(message = "message content must not be null")
    private String content;
    @NotNull(message = "message dateTime must not be null")
    private LocalDateTime dateTime;
    @NotNull(message = "message senderId must not be null")
    private Long senderId;
    private Long receiverId;
    private Long groupId;
    @Column(name = "group?")
    @NotNull(message = "message group must not be null")
    private Boolean group;
}
