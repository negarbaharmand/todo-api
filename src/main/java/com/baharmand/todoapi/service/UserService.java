package com.baharmand.todoapi.service;

import com.baharmand.todoapi.domain.dto.UserDTOForm;
import com.baharmand.todoapi.domain.dto.UserDTOView;

public interface UserService {
    UserDTOView register(UserDTOForm userDTOForm);

    UserDTOView getByEmail(String email);

    void disableByEmail(String email);

    void enableByEmail(String email);
}
