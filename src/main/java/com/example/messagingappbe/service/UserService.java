package com.example.messagingappbe.service;

import com.example.messagingappbe.repository.UserRepository;
import com.example.messagingappbe.request.CommonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // update user detail api
    public static String updateUser(@RequestBody CommonRequest commonRequest) {
        return null;
    }
    // change password api
    public static String updatePassword(@RequestBody CommonRequest commonRequest) {
        return null;
    }
    // forget password api
    public static String forgetPassword(@RequestBody CommonRequest commonRequest) {
        return null;
    }
    // add contact api (by email address or by id)
    public static String addContact(@RequestBody CommonRequest commonRequest) {
        return null;
    }


//    public static CommonResponse register(RegisterRequest registerRequest) {
//        String username = registerRequest.getUsername();
//        String password = registerRequest.getPassword();
//        if (username == null || password == null) {
//            return CommonResponse.fail(400, "username or password must not be null");
//        }
//        Boolean duplicateEmailAddress = userRepository.existsByEmailAddress(registerRequest.getEmailAddress());
//        if (duplicateEmailAddress) {
//            return CommonResponse.fail(400, "email address already registered in the system");
//        }
//        var newUser = User.builder()
//                        .name(username)
//                        .password(password)
//                        .phoneNumber(registerRequest.getPhoneNumber())
//                        .emailAddress(registerRequest.getEmailAddress())
//                        .avatar(registerRequest.getAvatar())
//                        .build();
//        userRepository.save(newUser);
//        return CommonResponse.success("Register successful");
//    }
//
//    public static AuthenticationResponse login(LoginRequest loginRequest) {
//        String emailAddress = loginRequest.getEmailAddress();
//        String password = loginRequest.getPassword();
//        if (emailAddress == null || password == null) {
//            return AuthenticationResponse.fail(400, "username or password must not be null");
//        }
//        Optional<User> userRecord = userRepository.findByEmailAddress(emailAddress);
//        if (userRecord.isEmpty()) {
//            return AuthenticationResponse.fail(400, "email address does not exists in the system");
//        }
//        String actualPassword = userRecord.get().getPassword();
//        if (password != actualPassword) {
//            return AuthenticationResponse.fail(400, "email address or password incorrect");
//        }
//        return AuthenticationResponse.success("fakeAccessToken", "fakeRefreshToken", Long.valueOf(1), "fakeDisplayName");
//    }
}
