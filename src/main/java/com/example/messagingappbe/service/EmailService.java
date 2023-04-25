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

import java.util.Date;
import java.util.Optional;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;
    private RegisterVerificationTokenRepository registerVerificationTokenRepository;
    private UserRepository userRepository;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, RegisterVerificationTokenRepository registerVerificationTokenRepository, UserRepository userRepository) {
        this.javaMailSender = javaMailSender;
        this.registerVerificationTokenRepository = registerVerificationTokenRepository;
        this.userRepository = userRepository;
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

        Date current = new Date();
        if (token.getExpiryDate().before(current)) {
            return CommonResponse.fail(400, "Token has already expired");
        }

        Optional<User> user = userRepository.findByEmailAddress(token.getUser().getEmailAddress());
        if (user.get() == null) {
            return CommonResponse.fail(400, "Email verification failed");
        }

        user.get().setVerified(true);
        userRepository.save(user.get());
        return CommonResponse.success("Email verified successfully!");
    }
}
