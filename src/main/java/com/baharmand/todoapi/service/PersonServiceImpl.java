package com.baharmand.todoapi.service;

import com.baharmand.todoapi.domain.entity.Person;
import com.baharmand.todoapi.domain.entity.Task;
import com.baharmand.todoapi.domain.entity.User;
import com.baharmand.todoapi.exception.DataNotFoundException;
import com.baharmand.todoapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person createPerson(String name, User user) {
        Person person = new Person(name, user);
        return personRepository.save(person);
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Person not found with id: " + id));
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public void addTaskToPerson(Person person, Task task) {
        person.addTask(task);
        personRepository.save(person);
    }

    @Override
    public void removeTaskFromPerson(Person person, Task task) {
        person.removeTask(task);
        personRepository.save(person);
    }

    @Override
    public Person updatePersonInformation(Long id, String name, User user) {
        Person person = getPersonById(id);
        person.setName(name);
        person.setUser(user);
        return personRepository.save(person);
    }

    @Override
    public void deletePersonById(Long id) {
        Person person = getPersonById(id);
        personRepository.delete(person);
    }

    @Override
    public boolean isPersonIdle(Long id) {
        Person person = getPersonById(id);
        return person.getTasks().isEmpty();
    }
}
