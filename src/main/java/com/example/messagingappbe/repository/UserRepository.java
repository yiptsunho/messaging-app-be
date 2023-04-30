package com.example.messagingappbe.repository;

import com.example.messagingappbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmailAddress(String emailAddress);

    Optional<User> findByEmailAddress(String emailAddress);

//    @Query(value = "SELECT * FROM user WHERE id IN (:userIdList)", nativeQuery = true)
//    List<User> findAllByIdList(@Param("userIdList") Collection<Long> userIdList);

    Long countAllByIdIn(Collection<Long> id);

//    @Query(value = "SELECT count(id) = 2 FROM user WHERE id in (:idList) ", nativeQuery = true)
//    Boolean existByIdList(@Param("idList") String idList);
}
