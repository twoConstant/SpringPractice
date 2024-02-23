package com.example.demo.service;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ValidationService validationService;

    public UserResponse saveUser(UserRequest request) {

        User user = userRepository.save(request.toUser());
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public UserResponse findUserById(Long userId) {
        return userRepository.findById(userId)
                .map((user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .build()))
                .orElseThrow(() -> new IllegalArgumentException("there is no user whit your request user_id"));
    }

    // 유저 전체 조회
    public List<UserResponse> findAllUser() {
        return userRepository.findAll()
                .stream()
                .map((user) -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .build())
                .toList();
    }

    // 유저 삭제
    public void  deleteUserById(Long userId) {
        // 해당 엔티티가 있는지 확인
        if (validationService.isValidUserId(userId)) {
            userRepository.deleteById(userId);
            return;
        }
        throw new IllegalArgumentException("there is no User whit your request id");
    }
}
