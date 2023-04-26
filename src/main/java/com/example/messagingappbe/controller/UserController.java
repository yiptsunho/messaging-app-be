package com.example.messagingappbe.controller;

import com.example.messagingappbe.request.CommonRequest;
import com.example.messagingappbe.request.LoginRequest;
import com.example.messagingappbe.request.RegisterRequest;
import com.example.messagingappbe.response.AuthenticationResponse;
import com.example.messagingappbe.response.CommonResponse;
import com.example.messagingappbe.service.AuthenticationService;
import com.example.messagingappbe.service.EmailService;
import com.example.messagingappbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;
    private EmailService emailService;

    @Autowired
    public void UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public CommonResponse register(@RequestBody RegisterRequest registerRequest) {
        return AuthenticationService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return AuthenticationService.login(loginRequest);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public CommonResponse confirmAccount(@RequestParam("token") String token) {
        return emailService.verifyAccount(token);
    }

    @PostMapping("/resendVerification")
    public CommonResponse resendVerificationEmail(@RequestParam Long id) {
        return AuthenticationService.resendVerificationEmail(id);
    }

    @PutMapping("/user")
    public String updateUser(@RequestBody CommonRequest commonRequest) {
        return UserService.updateUser(commonRequest);
    }

    @PutMapping("/password")
    public String updatePassword(@RequestBody CommonRequest commonRequest) {
        return UserService.updatePassword(commonRequest);
    }

    @PostMapping("/password")
    public String forgetPassword(@RequestBody CommonRequest commonRequest) {
        return UserService.forgetPassword(commonRequest);
    }

    @PostMapping("/add")
    public String addContact(@RequestBody CommonRequest commonRequest) {
        return UserService.addContact(commonRequest);
    }

    @PostMapping("/accept")
    public CommonResponse acceptChatRequest(@RequestParam Long id) {
        return null;
    }

}
