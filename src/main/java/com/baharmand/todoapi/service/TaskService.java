package com.baharmand.todoapi.service;

import com.baharmand.todoapi.domain.entity.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    List<Task> getTasksWithTitle(String title);

    List<Task> getTasksByPersonId(Long personId);

    List<Task> getTasksByDone(boolean done);

    List<Task> getTasksBetweenDates(LocalDate startDate, LocalDate endDate);

    List<Task> getTasksByDeadline(LocalDate deadline);

    List<Task> getTasksWithNoAssignee();

    List<Task> getIncompleteTasks();
}
