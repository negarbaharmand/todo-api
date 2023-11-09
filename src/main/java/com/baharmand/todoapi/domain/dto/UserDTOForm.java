package com.baharmand.todoapi.domain.dto;

import lombok.*;

import java.util.Set;

@Data
public class UserDTOForm {
    private String email;
    private String password;
    private Set<RoleDTOForm> roles;
}

