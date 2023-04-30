package com.example.messagingappbe.repository;

import com.example.messagingappbe.model.RegisterVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RegisterVerificationTokenRepository extends JpaRepository<RegisterVerificationToken, Long> {
    RegisterVerificationToken findByToken(String registerVerificationToken);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "UPDATE register_verification_token SET active = 0 WHERE user_id = :id", nativeQuery = true)
    void invalidateAllCurrentToken(@Param("id") Long id);
}
