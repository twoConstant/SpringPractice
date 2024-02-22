package com.example.demo.dto.request;

import com.example.demo.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Getter
@Setter
public class AddUserRequest {
    private String name;

    public User toUser() {
        return User.builder()
                .name(this.name)
                .build();
    }
}
