package com.example.messagingappbe.repository;

import com.example.messagingappbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmailAddress(String emailAddress);

    Optional<User> findByEmailAddress(String emailAddress);
}
