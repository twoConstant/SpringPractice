package com.example.demo.controller;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 유저 생성
    @PostMapping("/users/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.saveUser(request);
        return ResponseEntity.ok(userResponse);
    }

    // 유저 단일 조회
    @GetMapping("/users/{user_id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable("user_id") Long userId) {
        UserResponse userResponse = userService.findUserById(userId);
        return ResponseEntity.ok(userResponse);
    }


    // 유저 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> findAllUser() {
        return ResponseEntity.ok(userService.findAllUser());
    }
    // 유저 삭제
    @DeleteMapping("/users/{user_id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("user_id") Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();

    }

}
