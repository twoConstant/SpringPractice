package com.example.demo.controller;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService memberService;

    // 회원 가입
    @PostMapping("/member/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody UserRequest request) {
        User user = memberService.saveMember(request);
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
        return ResponseEntity.ok(userResponse);
    }

}
