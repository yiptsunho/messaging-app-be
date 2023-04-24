package com.example.messagingappbe.repository;

import com.example.messagingappbe.model.RegisterVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterVerificationTokenRepository extends JpaRepository<RegisterVerificationToken, Long> {
    RegisterVerificationToken findByToken(String registerVerificationToken);
}
