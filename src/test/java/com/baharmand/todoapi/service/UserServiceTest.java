package com.baharmand.todoapi.service;

import com.baharmand.todoapi.converter.UserConverter;
import com.baharmand.todoapi.domain.dto.RoleDTOForm;
import com.baharmand.todoapi.domain.dto.UserDTOForm;
import com.baharmand.todoapi.domain.dto.UserDTOView;
import com.baharmand.todoapi.domain.entity.Role;
import com.baharmand.todoapi.domain.entity.User;
import com.baharmand.todoapi.exception.DataDuplicateException;
import com.baharmand.todoapi.repository.RoleRepository;
import com.baharmand.todoapi.repository.UserRepository;
import com.baharmand.todoapi.utility.CustomPasswordEncoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private CustomPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void Register_ValidUserDTOForm_ReturnsUserDTOView() {
        // Arrange
        // Create a UserDTOForm with a sample email, password, and associated role
        Set<RoleDTOForm> roleDTOForms = new HashSet<>();
        roleDTOForms.add(RoleDTOForm.builder().id(1L).name("ROLE_USER").build());
        UserDTOForm userDTOForm = UserDTOForm.builder()
                .email("test@test.se")
                .password("password")
                .roles(roleDTOForms)
                .build();
        // Create a sample role for your test
        Role roleEntity = new Role(1L, "ROLE_USER");

        // Mock the behavior of passwordEncoder.encode
        String pwEncoder = "encodedPassword";
        when(passwordEncoder.encode("password")).thenReturn(pwEncoder);

        // Create a user entity with the expected encoded password and associated role
        User userEntity = new User(userDTOForm.getEmail(), pwEncoder);
        userEntity.addRole(roleEntity);

        // Define behavior for mock objects
        // Mock the repository methods to return expected values
        when(userRepository.existsByEmail(userDTOForm.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(userEntity);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleEntity));

        // Act
        UserDTOView savedUser = userService.register(userDTOForm);
        // Verify that specific methods were called
        // Ensure that the userService interacts with the mock objects as expected
        verify(userRepository).existsByEmail(userDTOForm.getEmail());
        verify(userRepository).save(any(User.class));
        verify(roleRepository).findById(1L);
        verify(passwordEncoder).encode("password");

        // Assertions
        // Check the result returned by userService.register
        assertNotNull(savedUser);
        assertEquals(userDTOForm.getEmail(), savedUser.getEmail());
        assertNotNull(roleRepository.findById(1L));
        assertEquals(pwEncoder, userEntity.getPassword());
        assertEquals("test@test.se", userEntity.getEmail());

    }

    @Test
    void register_DuplicateEmail_ShouldThrowDataDuplicateException() {
        // Arrange
        String duplicateEmail = "test@example.com";
        Set<RoleDTOForm> roleDTOForms = Collections.singleton(RoleDTOForm.builder().id(1L).name("ROLE_USER").build());
        UserDTOForm userDTOForm = UserDTOForm.builder()
                .email(duplicateEmail)
                .password("password")
                .roles(roleDTOForms)
                .build();

        // Mock the behavior of userRepository.existsByEmail to return true, indicating a duplicate email
        when(userRepository.existsByEmail(duplicateEmail)).thenReturn(true);

        // Act and Assert
        assertThrows(DataDuplicateException.class, () -> {
            userService.register(userDTOForm);
        });

        // Verify that userRepository.existsByEmail was called with the duplicate email
        verify(userRepository).existsByEmail(duplicateEmail);

    }

    @Test
    void register_InvalidRole_ShouldThrowDataNotFoundException() {
        // Arrange
        String validEmail = "test@example.com";

        // Create a UserDTOForm with an invalid role (a role that does not exist in the system)
        Set<RoleDTOForm> roleDTOForms = Collections.singleton(RoleDTOForm.builder().id(2L).name("ROLE_INVALID").build());
        UserDTOForm userDTOForm = UserDTOForm.builder()
                .email(validEmail)
                .password("password")
                .roles(roleDTOForms)
                .build();

        // Mock the behavior of userRepository.existsByEmail to return false, indicating a non-duplicate email
        when(userRepository.existsByEmail(validEmail)).thenReturn(false);

        // Mock the behavior of roleRepository.findById to return an empty Optional, indicating the role doesn't exist
        when(roleRepository.findById(2L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(DataDuplicateException.class, () -> {
            userService.register(userDTOForm);
        });

        // Verify that userRepository.existsByEmail was called with the valid email
        verify(userRepository).existsByEmail(validEmail);

        // Verify that roleRepository.findById was called with the role ID from the UserDTOForm
        verify(roleRepository).findById(2L);

    }

}
