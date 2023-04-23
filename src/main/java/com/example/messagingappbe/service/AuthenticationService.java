package com.example.messagingappbe.service;

import com.example.messagingappbe.model.User;
import com.example.messagingappbe.repository.UserRepository;
import com.example.messagingappbe.request.LoginRequest;
import com.example.messagingappbe.request.RegisterRequest;
import com.example.messagingappbe.response.AuthenticationResponse;
import com.example.messagingappbe.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private static UserRepository userRepository;
    private static PasswordEncoder passwordEncoder;
    private static JwtService jwtService;
    private static AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService (UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public static CommonResponse register(RegisterRequest registerRequest) {
        System.out.println("start register.....");
        String emailAddress = registerRequest.getEmailAddress();
        String password = registerRequest.getPassword();
        if (emailAddress == null || password == null) {
            return CommonResponse.fail(400, "username or password must not be null");
        }
        Boolean duplicateEmailAddress = userRepository.existsByEmailAddress(registerRequest.getEmailAddress());
        if (duplicateEmailAddress) {
            return CommonResponse.fail(400, "email address already registered in the system");
        }
        var newUser = User.builder()
                .name(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phoneNumber(registerRequest.getPhoneNumber())
                .emailAddress(emailAddress)
                .avatar(registerRequest.getAvatar())
                .build();
        userRepository.save(newUser);
        return CommonResponse.success("Register successful");
    }

    public static AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmailAddress(),
                        loginRequest.getPassword()
                )
        );
        var user = userRepository.findByEmailAddress(loginRequest.getEmailAddress())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .code(200)
                .status("success")
                .message("Login Success")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .displayName(user.getName())
                .build();
    }
}
