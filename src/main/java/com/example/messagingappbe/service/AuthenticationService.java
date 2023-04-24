package com.example.messagingappbe.service;

import com.example.messagingappbe.model.RegisterVerificationToken;
import com.example.messagingappbe.model.User;
import com.example.messagingappbe.repository.RegisterVerificationTokenRepository;
import com.example.messagingappbe.repository.UserRepository;
import com.example.messagingappbe.request.LoginRequest;
import com.example.messagingappbe.request.RegisterRequest;
import com.example.messagingappbe.response.AuthenticationResponse;
import com.example.messagingappbe.response.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
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
    private static EmailService emailService;
    private static RegisterVerificationTokenRepository registerVerificationTokenRepository;
    @Value("${domain.name}")
    private static String domain;

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
                .verified(false)
                .build();
        userRepository.save(newUser);

        RegisterVerificationToken registerVerificationToken = new RegisterVerificationToken(newUser);

        registerVerificationTokenRepository.save(registerVerificationToken);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(newUser.getEmailAddress());
        mail.setSubject("Complete registration!");
        mail.setText("To confirm your account, please click here :" + domain +
                "api/v1/user/confirm-account?token=" + registerVerificationToken.getToken());
        emailService.sendEmail(mail);

        return CommonResponse.success("Register successful! A verification email has been sent to the user's email address");
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
