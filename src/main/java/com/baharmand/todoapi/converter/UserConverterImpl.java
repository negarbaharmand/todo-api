package com.baharmand.todoapi.converter;

import com.baharmand.todoapi.domain.dto.RoleDTOView;
import com.baharmand.todoapi.domain.dto.UserDTOView;
import com.baharmand.todoapi.domain.entity.Role;
import com.baharmand.todoapi.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverterImpl implements UserConverter {

    private RoleConverter roleConverter;

    @Autowired

    public UserConverterImpl(RoleConverter roleConverter) {
        this.roleConverter = roleConverter;
    }


    @Override
    public UserDTOView toUserDTOView(User entity) {
        UserDTOView dtoView = new UserDTOView();
        dtoView.setEmail(entity.getEmail());

        Set<RoleDTOView> roles = entity.getRoles()
                .stream()
                .map(roleConverter::toRoleDTOView)
                .collect(Collectors.toSet());

        dtoView.setRoles(roles);

        return dtoView;
    }

    @Override
    public User toUserEntity(UserDTOView dtoView) {
        User user = new User();
        user.setEmail(dtoView.getEmail());

        Set<Role> roles = dtoView.getRoles()
                .stream()
                .map(roleConverter::toRoleEntity) // Assuming roleConverter is an instance of RoleConverter
                .collect(Collectors.toSet());

        user.setRoles(roles);

        return user;
    }
}
