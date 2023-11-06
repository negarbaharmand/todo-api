package com.baharmand.todoapi.service;

import com.baharmand.todoapi.domain.entity.Person;
import com.baharmand.todoapi.domain.entity.Task;
import com.baharmand.todoapi.domain.entity.User;

import java.util.List;

public interface PersonService {

    Person createPerson(String name, User user);

    Person getPersonById(Long id);

    List<Person> getAllPersons();

    void addTaskToPerson(Person person, Task task);

    void removeTaskFromPerson(Person person, Task task);

    Person updatePersonInformation(Long id, String name, User user);

    void deletePersonById(Long id);

    boolean isPersonIdle(Long id);
}


