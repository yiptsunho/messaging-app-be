package com.example.messagingappbe.repository;

import com.example.messagingappbe.dto.MessageDto;
import com.example.messagingappbe.dto.RecentChatDto;
import com.example.messagingappbe.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
//    @Query(value = "SELECT com.example.messagingappbe.dto.MessageDto(m.id, m.type, m.content, m.dateTime, m.sender, m.receiver) FROM Message m WHERE m.sender in (:senderId, :receiverId) AND m.receiver in (:senderId, :receiverId)")
//    List<MessageDto> findAllBySenderIdAndReceiverId(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

    @Query(value =
            "SELECT MAX(id) FROM message " +
            "WHERE sender_id = :id OR receiver_id = :id " +
            "GROUP BY sender_id, receiver_id, group_id " +
            "LIMIT 20", nativeQuery = true)
    List<Long> findRecentMessageIdList(@Param("id") Long requesterId);

    @Query(value =
            "SELECT new com.example.messagingappbe.dto.RecentChatDto(m.id, m.type, m.content, m.dateTime, m.sender.id, m.receiver.id, m.group.id, m.isGroup,  u.name, u.avatar) FROM Message m " +
            "LEFT JOIN User u " +
            "ON CASE WHEN (m.sender.id = :requesterId) THEN m.receiver.id ELSE m.sender.id END = u.id " +
            "LEFT JOIN Group g " +
            "ON m.group.id = g.id " +
            "WHERE m.id in (:idList) " +
            "ORDER BY m.dateTime DESC")
//            "SELECT message.*, user.name, user.avatar FROM message " +
//            "LEFT JOIN user " +
//            "ON IF (message.sender_id = :requesterId, message.receiver_id, message.sender_id) = user.id " +
//            "LEFT JOIN chat_group " +
//            "ON message.group_id = chat_group.id " +
//            "WHERE message.id in (:idList) " +
//            "ORDER BY date_time DESC", nativeQuery = true)
    List<RecentChatDto> findRecentMessageList(@Param("requesterId") Long id, @Param("idList") List<Long> idList);

    @Query(value =
            "SELECT new com.example.messagingappbe.dto.MessageDto(m.id, m.type, m.content, m.dateTime, m.sender.id, m.receiver.id) " +
            "FROM Message m " +
            "WHERE m.sender.id in (:senderId, :receiverId) AND m.receiver.id in (:senderId, :receiverId)")
    List<MessageDto> findAllBySenderIdAndReceiverId(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
    @Query(value =
            "SELECT new com.example.messagingappbe.dto.MessageDto(m.id, m.type, m.content, m.dateTime, m.sender.id, m.receiver.id, m.group.id) " +
            "FROM Message m " +
            "WHERE m.isGroup = true AND m.group.id = :groupId")
    List<MessageDto> findAllByGroupId(@Param("groupId") Long groupId);
}
