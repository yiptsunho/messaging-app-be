package com.example.messagingappbe.controller;

import com.example.messagingappbe.response.CommonResponse;
import com.example.messagingappbe.service.ContactRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/contact")
public class ContactRequestController {

    private ContactRequestService contactRequestService;

    public ContactRequestController(ContactRequestService contactRequestService) {
        this.contactRequestService = contactRequestService;
    }

    @PostMapping("")
    public CommonResponse addContact(@RequestParam Long userId, Long contactId) {
        return contactRequestService.addContact(userId, contactId);
    }

    @PutMapping("/accept")
    public CommonResponse acceptChatRequest(@RequestParam Long id) {
        return contactRequestService.acceptChatRequest(id);
    }

    @PutMapping("/reject")
    public CommonResponse rejectChatRequest(@RequestParam Long id) {
        return contactRequestService.rejectChatRequest(id);
    }
}
