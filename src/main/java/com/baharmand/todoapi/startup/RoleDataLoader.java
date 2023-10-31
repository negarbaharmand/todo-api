package com.baharmand.todoapi.startup;

import com.baharmand.todoapi.entity.Role;
import com.baharmand.todoapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
//CommandLine interface has a run method which will be executed when our app starts
public class RoleDataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("GUEST"));

    }
}
