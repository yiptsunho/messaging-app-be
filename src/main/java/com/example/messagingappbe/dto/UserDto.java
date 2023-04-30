package com.example.messagingappbe.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String name;
    private byte[] avatar;

    public UserDto(String name, byte[] avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public UserDto(Long id, String name, byte[] avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }
}
