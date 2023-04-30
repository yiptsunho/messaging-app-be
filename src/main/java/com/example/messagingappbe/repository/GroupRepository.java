package com.example.messagingappbe.repository;

import com.example.messagingappbe.dto.GroupDto;
import com.example.messagingappbe.dto.UserDto;
import com.example.messagingappbe.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query(value =
            "SELECT new com.example.messagingappbe.dto.UserDto(u.id, u.name, u.avatar) " +
            "FROM User u WHERE u.id = :id")
    List<UserDto> findUserByGroupId(@Param("id") Long id);

    @Query(value = "SELECT u.id FROM User u WHERE u.id = :id")
    List<Long> findUserIdListByGroupId(@Param("id") Long id);

    @Query(value =
            "SELECT new com.example.messagingappbe.dto.GroupDto(g.name, g.avatar) " +
            "FROM Group g WHERE g.id = :id")
    GroupDto findGroupById(@Param("id") Long id);
}
