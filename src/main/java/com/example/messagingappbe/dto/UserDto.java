package com.example.messagingappbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String name;

    public UserDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
