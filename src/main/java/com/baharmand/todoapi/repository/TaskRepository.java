package com.baharmand.todoapi.repository;

import com.baharmand.todoapi.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitleContains(String title);

    // First way:
    @Query("select t from Task t where t.person.id = :personId")
    List<Task> findTasksByPersonId(@Param("personId") Long personId);
    //Second way: List<Task> findByPerson_Id(Long personId)

    List<Task> findByDone(boolean done);

    @Query("select t from Task t where t.deadline between :startDate and :endDate")
    List<Task> selectTasksBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    //OR:List<Task> findByDeadLineBetween(LocalDate from, LocalDate to)

    @Query("select t from Task t where t.deadline = :deadline")
    List<Task> findTasksByDeadline(@Param("deadline") LocalDate deadline);

    List<Task> findByPersonIsNull();

    List<Task> findByDoneFalse();

}
