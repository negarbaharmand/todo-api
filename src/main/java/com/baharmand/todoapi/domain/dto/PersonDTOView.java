package com.baharmand.todoapi.domain.dto;

import com.baharmand.todoapi.domain.entity.User;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PersonDTOView {
    private String name;
    private User user;
}
