package com.example.messagingappbe.service;

import com.example.messagingappbe.dto.MessageDto;
import com.example.messagingappbe.dto.RecentChatDto;
import com.example.messagingappbe.dto.UserDto;
import com.example.messagingappbe.repository.GroupRepository;
import com.example.messagingappbe.repository.MessageRepository;
import com.example.messagingappbe.repository.UserRepository;
import com.example.messagingappbe.request.CommonRequest;
import com.example.messagingappbe.response.CommonResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private static MessageRepository messageRepository;
    private static GroupRepository groupRepository;
    private static UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public static CommonResponse getRecentChat(Long requestedId) {
        if (requestedId == null) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        Boolean exists = userRepository.existsById(requestedId);
        if (!exists) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        List<Long> chatIdList = messageRepository.findRecentMessageIdList(requestedId);
        List<RecentChatDto> chatList = messageRepository.findRecentMessageList(requestedId, chatIdList);

        return CommonResponse.success(chatList);
    }

    public static CommonResponse getPrivateMessage(Long senderId, Long receiverId) {
        if (senderId == null || receiverId == null) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        List<MessageDto> messageDtoList = messageRepository.findAllBySenderIdAndReceiverId(senderId, receiverId);

        return CommonResponse.success(messageDtoList);
    }

    public static CommonResponse getGroupMessage(Long requesterId, Long groupId) {
        if (requesterId == null || groupId == null) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        List<Long> userIdList = groupRepository.findUserIdListByGroupId(groupId);
        Boolean isGroupMember = userIdList.contains(requesterId);
        if (!isGroupMember) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        List<MessageDto> messageDtoList = messageRepository.findAllByGroupId(groupId);
        return CommonResponse.success(messageDtoList);
    }

    public static String updateMessage(CommonRequest commonRequest) {
        return null;
    }

    public static String deleteMessage(Long messageId) {
        return null;
    }
}
