package com.abbys.bms.data.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public abstract class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String role;
}
