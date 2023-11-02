package com.baharmand.todoapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "tasks, user")

@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "email")
    private User user;

    @OneToMany(mappedBy = "person")
    private List<Task> tasks = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public void addTask(Task... tasks) {
        if (Objects.requireNonNull(tasks).length == 0)
            throw new IllegalArgumentException("Task is empty");
        for(Task task : tasks){
            this.tasks.add(task);
            if(task.getPerson() !=null) {
                task.setPerson(this);
            }
        }
    }

    public void removeTask(Task task) {
        if (this.tasks.remove(task) && task.getPerson() == this) {
            task.setPerson(null);
        }
    }
}
