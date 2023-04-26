package com.example.messagingappbe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "message type must not be null")
    private String type;

    @NotNull(message = "message content must not be null")
    private String content;

    @NotNull(message = "message dateTime must not be null")
    private LocalDateTime dateTime;

    @NotNull(message = "message senderId must not be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    @NotNull(message = "message isGroup must not be null")
    private Boolean isGroup;
}
