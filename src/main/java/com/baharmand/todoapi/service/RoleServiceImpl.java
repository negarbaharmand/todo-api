package com.baharmand.todoapi.service;

import com.baharmand.todoapi.converter.RoleConverter;
import com.baharmand.todoapi.domain.dto.RoleDTOView;
import com.baharmand.todoapi.domain.entity.Role;
import com.baharmand.todoapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleConverter roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }


    @Override
    public List<RoleDTOView> getAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTOView> roleDTOList = new ArrayList<>();
        for (Role entity : roles) {
            roleDTOList.add(roleConverter.toRoleDTOView(entity));
        }
        return roleDTOList;
    }
}
