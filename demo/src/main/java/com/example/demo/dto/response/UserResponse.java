package com.example.demo.dto.response;

import com.example.demo.repository.UserRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;

    @Builder
    public UserResponse(Long id, String name) {
        this.id =id;
        this.name = name;
    }
}
