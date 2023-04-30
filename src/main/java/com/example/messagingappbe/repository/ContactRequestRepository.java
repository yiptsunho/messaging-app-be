package com.example.messagingappbe.repository;

import com.example.messagingappbe.model.ContactRequest;
import com.example.messagingappbe.model.ContactRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.messagingappbe.model.ContactRequestStatus.PENDING;

@Repository
public interface ContactRequestRepository extends JpaRepository<ContactRequest, Long> {

    @Query(value = "SELECT * FROM contacts WHERE id = :userId AND contact_id = :contactId", nativeQuery = true)
    Optional<Object> findContactByIdAndContactId(@Param("userId") Long userId, @Param("contactId") Long contactId);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "INSERT INTO contacts (id, contact_id) VALUES (:userId, :contactId)", nativeQuery = true)
//    void addContact(@Param("userId") Long userId, @Param("contactId") Long contactId);

    Boolean existsByIdAndStatus(Long id, ContactRequestStatus contactRequestStatus);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE contact_request SET status = :status  WHERE id = :id", nativeQuery = true)
    void update(@Param("status") Integer statusOrdinal, @Param("id") Long id);
}
