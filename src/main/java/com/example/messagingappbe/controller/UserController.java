package com.example.messagingappbe.controller;

import com.example.messagingappbe.model.User;
import com.example.messagingappbe.request.LoginRequest;
import com.example.messagingappbe.response.CommonResponse;
import com.example.messagingappbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    public UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public CommonResponse login(@RequestBody LoginRequest loginRequest) {
        return CommonResponse.builder()
                .code(200)
                .status("Request success")
                .message("User login successful")
                .build();
    }

}
