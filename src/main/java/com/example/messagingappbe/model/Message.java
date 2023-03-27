package com.example.messagingappbe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private Long id;
    private String type;
    private String content;
    private LocalDateTime time;
    private Long user_id;
}
