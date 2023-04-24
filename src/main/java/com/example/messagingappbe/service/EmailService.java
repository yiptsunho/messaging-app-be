package com.example.messagingappbe.service;

import com.example.messagingappbe.model.RegisterVerificationToken;
import com.example.messagingappbe.model.User;
import com.example.messagingappbe.repository.RegisterVerificationTokenRepository;
import com.example.messagingappbe.repository.UserRepository;
import com.example.messagingappbe.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;
    private RegisterVerificationToken registerVerificationToken;
    private RegisterVerificationTokenRepository registerVerificationTokenRepository;
    private UserRepository userRepository;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage mail) {
        javaMailSender.send(mail);
    }

    public CommonResponse verifyEmail(String registerVerificationToken) {
        RegisterVerificationToken token = registerVerificationTokenRepository.findByToken(registerVerificationToken);

        if (token == null) {
            return CommonResponse.fail(400, "Email verification failed");
        }

        Optional<User> user = userRepository.findByEmailAddress(token.getUser().getEmailAddress());
        if (user.get() != null) {
            return CommonResponse.fail(400, "Email verification failed");
        }

        user.get().setVerified(true);
        return CommonResponse.success("Email verified successfully!");
    }
}