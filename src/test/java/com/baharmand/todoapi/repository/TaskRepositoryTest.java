package com.baharmand.todoapi.repository;

import com.baharmand.todoapi.domain.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    Task task1;
    Task task2;

    @BeforeEach
    public void setup() {
        task1 = new Task("Task 1", "Do task 1", LocalDate.of(2023, 10, 31));
        task2 = new Task("Task 2", "Do task 2", LocalDate.of(2023, 10, 31));
        taskRepository.save(task1);
        taskRepository.save(task2);
    }

    @Test
    public void testFindTasksByDeadline() {

        List<Task> foundTasks = taskRepository.findTasksByDeadline(LocalDate.of(2023, 10, 31));
        assertEquals(2, foundTasks.size());
        assertNotNull(task1);
        assertNotNull(task2);
    }

    @Test
    public void testFindByTitleContains() {
        Task task3 = new Task("Another one", "Do another task", LocalDate.of(2023, 10, 31));
        taskRepository.save(task3);
        List<Task> foundTasks = taskRepository.findByTitleContains("Task");
        assertEquals(2, foundTasks.size());

         }
}
