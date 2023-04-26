package com.example.messagingappbe.request;

import com.example.messagingappbe.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest {

    @NotNull(message = "group name must not be null")
    private String name;
    private byte[] avatar;
    @NotNull(message = "user list must not be null")
    private ArrayList<Long> userList;
}
