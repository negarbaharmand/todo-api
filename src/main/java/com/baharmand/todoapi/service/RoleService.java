package com.baharmand.todoapi.service;

import com.baharmand.todoapi.domain.dto.RoleDTOView;
import com.baharmand.todoapi.domain.entity.Role;

import java.util.List;

public interface RoleService {
    List<RoleDTOView> getAll();
}
