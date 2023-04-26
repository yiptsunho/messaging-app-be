package com.example.messagingappbe.service;

import com.example.messagingappbe.dto.GroupInfoDto;
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

    private ModelMapper modelMapper;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public CommonResponse getGroupInfo(Long id) {
        Boolean exists = groupRepository.existsById(id);
        if (!exists) {
            return CommonResponse.fail(400, "invalid group id");
        }

        Optional<Group> group = groupRepository.findById(id);
        List<UserDto> userList = groupRepository.findUserInGroupByGroupId(id);

//        List<UserDto> userDtoList = userList.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
        GroupInfoDto groupInfoDto = modelMapper.map(group, GroupInfoDto.class);
        groupInfoDto.setUserList(userList);

        return CommonResponse.success(groupInfoDto);
    }

    public CommonResponse createGroup(GroupRequest groupRequest) {
        if (StringUtils.isEmpty(groupRequest.getName())) {
            return CommonResponse.fail(400, "group name must not be null or empty");
        }

        if (groupRequest.getUserList().isEmpty()) {
            return CommonResponse.fail(400, "user list must not be empty");
        }

        // block if user list contain duplicate id
        Set<Long> userIdList = new HashSet<>();
        for (Long id: groupRequest.getUserList()) {
            if (userIdList.contains(id)) {
                return CommonResponse.fail(400, "user list cannot contain duplicate id");
            }
            userIdList.add(id);
        }

        List<User> userList = userRepository.findAllByIdList(userIdList);
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
