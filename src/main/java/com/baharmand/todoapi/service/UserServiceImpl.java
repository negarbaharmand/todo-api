package com.baharmand.todoapi.service;

import com.baharmand.todoapi.converter.RoleConverter;
import com.baharmand.todoapi.converter.UserConverter;
import com.baharmand.todoapi.domain.dto.RoleDTOView;
import com.baharmand.todoapi.domain.dto.UserDTOForm;
import com.baharmand.todoapi.domain.dto.UserDTOView;
import com.baharmand.todoapi.domain.entity.Role;
import com.baharmand.todoapi.domain.entity.User;
import com.baharmand.todoapi.exception.DataDuplicateException;
import com.baharmand.todoapi.exception.DataNotFoundException;
import com.baharmand.todoapi.repository.RoleRepository;
import com.baharmand.todoapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserConverter userConverter;
    private final RoleConverter roleConverter;
    private final BCryptPasswordEncoder passwordEncoder; // Inject BCryptPasswordEncoder

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleConverter roleConverter, UserConverter userConverter, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userConverter = userConverter;
        this.roleConverter = roleConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTOView register(UserDTOForm userDTOForm) {
        if (userDTOForm == null) {
            throw new IllegalArgumentException("User form is null.");
        }

        // Check if email already exists
        boolean isExistEmail = userRepository.existsByEmail(userDTOForm.getEmail());
        if (isExistEmail) {
            throw new DataDuplicateException("Email is already in use.");
        }


        // Retrieve and validate roles
        Set<Role> roleList = userDTOForm.getRoles()
                .stream()
                .map(roleDTOForm -> roleRepository.findById(roleDTOForm.getId())
                        .orElseThrow(() -> new DataNotFoundException("Role is not valid.")))
                .collect(Collectors.toSet());

        // Hash the password
        String hashedPassword = passwordEncoder.encode(userDTOForm.getPassword());

        // Convert DTO to Entity
        User user = new User(userDTOForm.getEmail(), hashedPassword);
        user.setRoles(roleList);
        // Create a new User entity
        User savedUser = userRepository.save(user);

        // Convert saved user to DTO with roles
        UserDTOView dtoView = new UserDTOView();
        dtoView.setEmail(savedUser.getEmail());

        // Convert and set roles in the DTO
        Set<RoleDTOView> roleDTOViewList = savedUser.getRoles()
                .stream()
                .map(roleConverter::toRoleDTOView)
                .collect(Collectors.toSet());
        dtoView.setRoles(roleDTOViewList);

        return dtoView;
    }

    @Override
    public UserDTOView getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User with email " + email + " not found."));

        UserDTOView dtoView = userConverter.toUserDTOView(user);

        return dtoView;
    }

    @Override
    public void disableByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User with email " + email + " not found."));
        user.setExpired(true);
        userRepository.save(user);
    }

    @Override
    public void enableByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User with email " + email + " not found."));
        user.setExpired(false);
        userRepository.save(user);
    }
}
