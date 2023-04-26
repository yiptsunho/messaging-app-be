package com.example.messagingappbe.controller;

import com.example.messagingappbe.request.GroupRequest;
import com.example.messagingappbe.response.CommonResponse;
import com.example.messagingappbe.service.GroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/group")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping()
    public CommonResponse getGroupInfo(@RequestParam Long id) {
        return groupService.getGroupInfo(id);
    }

    @PostMapping()
    public CommonResponse createGroup(@RequestBody GroupRequest groupRequest) {
        System.out.println("Reached controller");
        return groupService.createGroup(groupRequest);
    }

    @PutMapping()
    public CommonResponse updateGroup(@RequestBody GroupRequest groupRequest) {
        return groupService.updateGroup(groupRequest);
    }

    @DeleteMapping()
    public CommonResponse deleteGroup(@RequestBody GroupRequest groupRequest) {
        return groupService.deleteGroup(groupRequest);
    }
}
