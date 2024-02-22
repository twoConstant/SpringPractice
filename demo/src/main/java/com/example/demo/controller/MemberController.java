package com.example.demo.controller;

import com.example.demo.dto.request.AddUserRequest;
import com.example.demo.entity.User;
import com.example.demo.service.MemberService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 회원 가입
    @PostMapping("/member/signup")
    public ResponseEntity<User> signup(@RequestBody AddUserRequest request) {
        User user = memberService.saveMember(request);
        return ResponseEntity.ok(user);
    }

}
