package com.api.hateoas.dto;

import com.api.hateoas.util.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String email;
    private String name;
    private Role role;
}
