package com.baharmand.todoapi.repository;

import com.baharmand.todoapi.entity.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);
}
