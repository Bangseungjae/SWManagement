package com.example.swmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;


    @Column
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column
    private String taskName;

    @Column
    private Long score;

    @Column
    private String description;

    @Column
    @ManyToOne
    private Project project;
}
