package com.baharmand.todoapi.converter;

import com.baharmand.todoapi.domain.dto.UserDTOView;
import com.baharmand.todoapi.domain.entity.User;

public interface UserConverter {
    UserDTOView toUserDTOView(User entity);
    User toUserEntity(UserDTOView dtoView);
}
