package com.baharmand.todoapi.domain.dto;

import com.baharmand.todoapi.domain.entity.User;
import lombok.Data;

@Data
public class PersonDTOForm {
    private String name;
    private User user;
}
