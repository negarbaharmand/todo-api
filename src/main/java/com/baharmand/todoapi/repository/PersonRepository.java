package com.baharmand.todoapi.repository;


import com.baharmand.todoapi.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  @Query("select p from Person p where size(p.tasks) = 0 ")
    List<Person> findIdlePeople();
}
