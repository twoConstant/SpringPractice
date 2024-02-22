package com.example.demo.service;

import com.example.demo.dto.request.AddUserRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public User saveMember(AddUserRequest request) {
        return memberRepository.save(request.toUser());

    }
}
