package com.baharmand.todoapi.service;

import com.baharmand.todoapi.domain.dto.PersonDTOForm;
import com.baharmand.todoapi.domain.dto.PersonDTOView;
import com.baharmand.todoapi.domain.entity.Person;
import com.baharmand.todoapi.exception.DataNotFoundException;
import com.baharmand.todoapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public PersonDTOView createPerson(PersonDTOForm personDTOForm) {
        if (personDTOForm == null) throw new IllegalArgumentException("person form is null.");
        Person person = new Person(personDTOForm.getName());
        Person savedPerson = personRepository.save(person);
        return PersonDTOView.builder()
                .id(savedPerson.getId())
                .name(person.getName())
                .build();
    }

    @Override
    public PersonDTOView getPersonById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Person not found with id: " + id));
        return PersonDTOView.builder().id(person.getId()).name(person.getName()).build();
    }

    @Override
    public List<PersonDTOView> getAllPersons() {
        List<Person> people = personRepository.findAll();
        List<PersonDTOView> personDTOList = new ArrayList<>();
        for (Person entity : people) {
            personDTOList.add(PersonDTOView.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build());
        }
        return personDTOList;
    }


    @Override
    @Transactional
    public PersonDTOView update(PersonDTOForm personDTOForm) {
        Person person = personRepository.findById(personDTOForm.getId()).orElseThrow(() -> new DataNotFoundException("Person Id is not valid."));
        return PersonDTOView.builder()
                .id(person.getId())
                .name(person.getName())
                .build();
    }

    @Override
    @Transactional
    public void deletePersonById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Person id is not valid."));
        personRepository.delete(person);
    }


}
