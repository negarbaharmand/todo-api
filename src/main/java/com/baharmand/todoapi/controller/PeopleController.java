package com.baharmand.todoapi.controller;


import com.baharmand.todoapi.domain.dto.PersonDTOForm;
import com.baharmand.todoapi.domain.dto.PersonDTOView;
import com.baharmand.todoapi.domain.entity.Person;
import com.baharmand.todoapi.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL
@RequestMapping("/api/v1/people")
@RestController
@Validated
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTOView>> doGetAllPersons(){
        List<PersonDTOView> responseBody = personService.getAllPersons();
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<PersonDTOView> doCreatePerson(@Valid @RequestBody PersonDTOForm personDTOForm){
        PersonDTOView responseBody = personService.createPerson(personDTOForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTOView> doGetPerson(@PathVariable Long id) {
        PersonDTOView person = personService.getPersonById(id);
        if (person !=null) {
            return ResponseEntity.ok(person);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<PersonDTOView> doUpdatePerson(@Valid @RequestBody PersonDTOForm personDTOForm) {
        PersonDTOView updatedPerson = personService.update(personDTOForm);
        if (updatedPerson != null) {
            return ResponseEntity.ok(updatedPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }

}
