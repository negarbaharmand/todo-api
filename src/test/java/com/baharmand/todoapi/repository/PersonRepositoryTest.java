package com.baharmand.todoapi.repository;


import com.baharmand.todoapi.domain.entity.Person;
import com.baharmand.todoapi.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;


    @Test
    @Transactional
    public void testFindIdlePeople(){
        User user1 = new User("user1@gmail.com","hfd43sf");
        User user2 = new User("user2@gmail.com","hfd43sf");

        Person person1 = new Person("Test",user1);
        Person person2 = new Person("Test2", user2);

        personRepository.save(person1);
        personRepository.save(person2);
        List<Person> idlePeople = personRepository.findIdlePeople();
        assertEquals(1, idlePeople.size());
        assertEquals("Test2", idlePeople.get(0).getName());
    }
}
