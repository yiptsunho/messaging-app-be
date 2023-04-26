package com.example.messagingappbe.repository;

import com.example.messagingappbe.dto.UserDto;
import com.example.messagingappbe.model.Group;
import com.example.messagingappbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query(value = "SELECT new com.example.messagingappbe.dto.UserDto(u.id, u.name) FROM User u")
    List<UserDto> findUserInGroupByGroupId(@Param("id") Long id);
}
