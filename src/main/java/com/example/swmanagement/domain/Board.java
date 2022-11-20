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
public class Board extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column
    private String name;

    @ManyToOne
    private Project project;

    @Column
    @Enumerated(EnumType.STRING)
    private PriorityStatus status;

    @Column
    private String description;

    @Column
    private Integer score;

    @Column
    private Boolean isStart;

    @OneToOne
    private Task task;

}
