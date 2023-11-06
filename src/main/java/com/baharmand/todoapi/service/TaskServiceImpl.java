package com.baharmand.todoapi.service;

import com.baharmand.todoapi.domain.entity.Task;
import com.baharmand.todoapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTasksWithTitle(String title) {
        return taskRepository.findByTitleContains(title);
    }

    @Override
    public List<Task> getTasksByPersonId(Long personId) {
        return taskRepository.findTasksByPersonId(personId);
    }

    @Override
    public List<Task> getTasksByDone(boolean done) {
        return taskRepository.findByDone(done);
    }

    @Override
    public List<Task> getTasksBetweenDates(LocalDate startDate, LocalDate endDate) {
        return taskRepository.selectTasksBetweenDates(startDate, endDate);
    }

    @Override
    public List<Task> getTasksByDeadline(LocalDate deadline) {
        return taskRepository.findTasksByDeadline(deadline);
    }

    @Override
    public List<Task> getTasksWithNoAssignee() {
        return taskRepository.findByPersonIsNull();
    }

    @Override
    public List<Task> getIncompleteTasks() {
        return taskRepository.findByDoneFalse();
    }
}
