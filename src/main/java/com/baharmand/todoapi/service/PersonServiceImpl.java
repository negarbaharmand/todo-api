package com.baharmand.todoapi.service;

import com.baharmand.todoapi.domain.dto.PersonDTOForm;
import com.baharmand.todoapi.domain.dto.PersonDTOView;
import com.baharmand.todoapi.domain.entity.Person;
import com.baharmand.todoapi.domain.entity.Task;
import com.baharmand.todoapi.domain.entity.User;
import com.baharmand.todoapi.exception.DataNotFoundException;
import com.baharmand.todoapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonDTOView createPerson(PersonDTOForm personDTOForm) {
        if (personDTOForm == null) throw new IllegalArgumentException("person form is null.");
        Person person = new Person(personDTOForm.getName(), personDTOForm.getUser());
        return PersonDTOView.builder()
                .name(person.getName())
                .user(person.getUser())
                .build();
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Person not found with id: " + id));
    }

    @Override
    public List<PersonDTOView> getAllPersons() {
        List<Person> people = personRepository.findAll();
        List<PersonDTOView> personDTOList = new ArrayList<>();
        for (Person entity : people) {
            personDTOList.add(PersonDTOView.builder()
                    .name(entity.getName())
                    .user(entity.getUser())
                    .build());
        }
        return personDTOList;
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
