package com.baharmand.todoapi.converter;

import com.baharmand.todoapi.domain.dto.RoleDTOView;
import com.baharmand.todoapi.domain.entity.Role;

public interface RoleConverter {
    RoleDTOView toRoleDTOView(Role entity);
    Role toRoleEntity(RoleDTOView dtoView);
}
