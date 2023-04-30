package com.example.messagingappbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private String groupName;
    private byte[] groupAvatar;
    private List<UserDto> userList;

    public GroupDto(String groupName, byte[] groupAvatar) {
        this.groupName = groupName;
        this.groupAvatar = groupAvatar;
    }
}
