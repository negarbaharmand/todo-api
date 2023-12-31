package com.baharmand.todoapi.repository;


import com.baharmand.todoapi.domain.entity.Person;
import com.baharmand.todoapi.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    @Transactional
    public void testFindIdlePeople() {
        User user1 = new User("user1@gmail.com", "hfd43sf");
        User user2 = new User("user2@gmail.com", "hfd43sf");
        userRepository.save(user1);
        userRepository.save(user2);

        Person person1 = new Person("Test1", user1);
        Person person2 = new Person("Test2", user2);

        personRepository.save(person1);
        personRepository.save(person2);

        List<Person> idlePeople = personRepository.findIdlePeople();
        assertEquals(2, idlePeople.size());
        assertEquals("Test1", idlePeople.get(0).getName());
    }
}
