package com.baharmand.todoapi.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(exclude = "person")
@ToString(exclude = "person")
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate deadline;
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Task(String title, String description, LocalDate deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }
}
