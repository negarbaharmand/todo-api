package com.baharmand.todoapi.repository;


import com.baharmand.todoapi.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("select p from Person p where size(p.tasks) = 0 ")
    List<Person> findIdlePeople();

    List<Person> findByName(String name);

    Person findByUserEmail(String email);

    @Query("select p from Person p where exists (select t from p.tasks t where t.done = false)")
    List<Person> findPeopleWithIncompleteTasks();

    @Query("select p from Person p join p.tasks t where t.id = :taskId")
    List<Person> findPeopleWithTask(@Param("taskId") Long taskId);

    @Query("select p from Person p where p.tasks is empty")
    List<Person> findPeopleWithNoTasks();

    Long countByName(String name);

}
