package com.baharmand.todoapi.service;

import com.baharmand.todoapi.domain.dto.PersonDTOForm;
import com.baharmand.todoapi.domain.dto.PersonDTOView;
import com.baharmand.todoapi.domain.entity.Person;
import com.baharmand.todoapi.domain.entity.Task;
import com.baharmand.todoapi.domain.entity.User;

import java.util.List;

public interface PersonService {

    PersonDTOView createPerson(PersonDTOForm personDTOForm);

    PersonDTOView getPersonById(Long id);

    List<PersonDTOView> getAllPersons();

    PersonDTOView update(PersonDTOForm personDTOForm);

    void deletePersonById(Long id);

}


