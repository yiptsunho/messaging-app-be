package com.example.messagingappbe.service;

import com.example.messagingappbe.model.ContactRequest;
import com.example.messagingappbe.model.ContactRequestStatus;
import com.example.messagingappbe.model.User;
import com.example.messagingappbe.repository.ContactRequestRepository;
import com.example.messagingappbe.repository.UserRepository;
import com.example.messagingappbe.response.CommonResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ContactRequestService {

    private static ContactRequestRepository contactRequestRepository;

    private static UserRepository userRepository;

    public ContactRequestService(ContactRequestRepository contactRequestRepository, UserRepository userRepository) {
        this.contactRequestRepository = contactRequestRepository;
        this.userRepository = userRepository;
    }

    public static CommonResponse addContact(Long userId, Long contactId) {
        if (userId == null || contactId == null || userId == contactId) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        List<User> existingUser = userRepository.findAllById(new ArrayList<>(Arrays.asList(userId, contactId)));
        Boolean bothUserExist = existingUser.size() == 2;
        if (!bothUserExist) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        Boolean contactExists = contactExists(userId, contactId);
        if (contactExists) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        ContactRequest contactRequest = ContactRequest.builder()
                .user(existingUser.stream().filter(user -> user.getId() == userId).findAny().get())
                .contact(existingUser.stream().filter(user -> user.getId() == contactId).findAny().get())
                .status(ContactRequestStatus.PENDING)
                .build();

        contactRequestRepository.save(contactRequest);

        return CommonResponse.success("Contact request created successfully!");
    }

    public static Boolean contactExists(Long userId, Long contactId) {;
        return contactRequestRepository.findContactByIdAndContactId(userId, contactId).isPresent();
    }

    public CommonResponse acceptChatRequest(Long id) {
        if (id == null) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        Boolean exists = contactRequestRepository.existsByIdAndStatus(id, ContactRequestStatus.PENDING);
        if (!exists) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        contactRequestRepository.update(ContactRequestStatus.ACCEPTED.ordinal(), id);

        return CommonResponse.success("Chat request approved");
    }

    public CommonResponse rejectChatRequest(Long id) {
        if (id == null) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        Boolean exists = contactRequestRepository.existsByIdAndStatus(id, ContactRequestStatus.PENDING);
        if (!exists) {
            return CommonResponse.fail(400, "invalid parameter");
        }

        contactRequestRepository.update(ContactRequestStatus.REJECTED.ordinal(), id);

        return CommonResponse.success("Chat request rejected");
    }
}
