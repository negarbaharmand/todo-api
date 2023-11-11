package com.baharmand.todoapi.domain.dto;

import com.baharmand.todoapi.domain.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PersonDTOForm {
    @PositiveOrZero(message = "ID must be a positive number or zero")
    private Long id;
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be at most 255 characters")
    private String name;
}
