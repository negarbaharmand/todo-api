package com.baharmand.todoapi.domain.dto;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTOView {

    private String email;
    private Set<RoleDTOView> roles;
}
