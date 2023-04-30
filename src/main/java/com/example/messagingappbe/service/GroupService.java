package com.example.messagingappbe.service;

import com.example.messagingappbe.dto.GroupDto;
import com.example.messagingappbe.dto.UserDto;
import com.example.messagingappbe.model.Group;
import com.example.messagingappbe.model.User;
import com.example.messagingappbe.repository.GroupRepository;
import com.example.messagingappbe.repository.UserRepository;
import com.example.messagingappbe.request.GroupRequest;
import com.example.messagingappbe.response.CommonResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Service
public class GroupService {

    private GroupRepository groupRepository;

    private UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public CommonResponse getGroupInfo(Long id) {
        Boolean exists = groupRepository.existsById(id);
        if (!exists) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        GroupDto groupDto = groupRepository.findGroupById(id);
        List<UserDto> userList = groupRepository.findUserByGroupId(id);

        Boolean isGroupMember = userList.stream().anyMatch(userDto -> userDto.getId() == id);
        if (!isGroupMember) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        groupDto.setUserList(userList);

        return CommonResponse.success(groupDto);
    }

    public CommonResponse createGroup(GroupRequest groupRequest) {
        if (StringUtils.isEmpty(groupRequest.getName())) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        if (groupRequest.getUserList().isEmpty()) {
            return CommonResponse.fail(400, "invalid parameter");
        }


        // block if user list contain duplicate id
        Set<Long> userIdList = new HashSet<>();
        for (Long id: groupRequest.getUserList()) {
            if (userIdList.contains(id)) {
                return CommonResponse.fail(400, "invalid parameter");
            }
            userIdList.add(id);
        }

        List<User> userList = userRepository.findAllById(userIdList);
        Boolean allUserExist = userList.size() == userIdList.size();
        if (!allUserExist) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        Group group = Group.builder()
                .name(groupRequest.getName())
                .avatar(groupRequest.getAvatar())
                .users(userList)
                .build();
        groupRepository.save(group);

        return CommonResponse.success("Create group success!");
    }

    public CommonResponse updateGroup(GroupRequest groupRequest) {
        return null;
    }

    public CommonResponse deleteGroup(GroupRequest groupRequest) {
        return null;
    }
}
