package com.baharmand.todoapi.controller;

import com.baharmand.todoapi.domain.dto.RoleDTOView;
import com.baharmand.todoapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL
@RequestMapping("/api/v1/roles")
@RestController
public class RoleController {

    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTOView>> doGetRoles() {
    List<RoleDTOView> responseBody = roleService.getAll();
    return ResponseEntity.ok().body(responseBody);
    }
}
