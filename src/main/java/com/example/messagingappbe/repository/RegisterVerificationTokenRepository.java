package com.example.messagingappbe.repository;

import com.example.messagingappbe.model.RegisterVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterVerificationTokenRepository extends JpaRepository<RegisterVerificationToken, Long> {
    RegisterVerificationToken findByToken(String registerVerificationToken);

    @Query(value = "UPDATE RegisterVerificationToken SET active = 0 WHERE  = :id")
    void invalidateAllCurrentToken(@Param("id") Long id);
}
