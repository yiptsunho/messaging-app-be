package com.example.messagingappbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupInfoDto {

    private String groupName;
    private byte[] groupAvatar;
    private List<UserDto> userList;
}
