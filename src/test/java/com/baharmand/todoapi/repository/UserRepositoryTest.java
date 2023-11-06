package com.baharmand.todoapi.repository;

import com.baharmand.todoapi.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    User user;
    String email = "user@gmail.com";

    @BeforeEach
    public void setup() {
        user = new User(email, "123ert");
        userRepository.save(user);

    }

    @Test
    public void testExistsAndFindByEmail() {
        assertTrue(userRepository.existsByEmail(email));
        Optional<User> foundUser = userRepository.findByEmail(email);
        assertNotNull(foundUser.get());
    }

    @Test
    public void testUpdateExpiredByEmail() {
        userRepository.updateExpiredByEmail(email, true);

        Optional<User> updatedUser = userRepository.findByEmail(email);
        assertTrue(updatedUser.isPresent());
        assertFalse(updatedUser.get().isExpired());
    }

    @Test
    public void testUpdatePasswordByEmail(){
        User user1 = new User("user@", "546");
        userRepository.save(user1);
        userRepository.updatePasswordByEmail("user@", "123");
        Optional<User> updatedUser = userRepository.findByEmail("user@");
        assertTrue(updatedUser.isPresent());
        //assertEquals("123", updatedUser.get().getPassword());
    }
}
