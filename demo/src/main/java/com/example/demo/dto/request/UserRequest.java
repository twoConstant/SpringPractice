package com.example.demo.dto.request;

import com.example.demo.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String name;

    public User toUser() {
        return User.builder()
                .name(this.name)
                .build();
    }
}
