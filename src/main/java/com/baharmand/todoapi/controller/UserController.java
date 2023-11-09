package com.baharmand.todoapi.controller;

import com.baharmand.todoapi.domain.dto.UserDTOForm;
import com.baharmand.todoapi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.baharmand.todoapi.domain.dto.UserDTOView;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/users")
@RestController //RestController annotation is to create RESTful web services
public class UserController {

    UserServiceImpl userService;

    @Autowired

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    // ResponseEntity is a spring http class that converts the UserDTOView Java model to a Json model
    @GetMapping
    public ResponseEntity<UserDTOView> doGetUserByEmail(@RequestParam String email) {
        System.out.println(">>>>>> getUserByEmail has been executed!");
        System.out.println("Email: " + email);

        UserDTOView responseBody = userService.getByEmail(email);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping
    public ResponseEntity<UserDTOView> doRegister(@RequestBody UserDTOForm userDTOForm) {
        System.out.println("DTO Form: " + userDTOForm);

        UserDTOView responseBody = userService.register(userDTOForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PutMapping("/disable")
    public ResponseEntity<Void> doDisableUserByEmail(@RequestParam("email") String email) {
        userService.disableByEmail(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/enable")
    public ResponseEntity<Void> doEnableUserByEmail(@RequestParam("email") String email) {
        userService.enableByEmail(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
