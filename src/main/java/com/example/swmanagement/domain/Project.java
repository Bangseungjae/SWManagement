package com.example.swmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "project_user",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> user;

    @OneToMany
    private List<Board> boards = new ArrayList<>();

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany
    private List<Issue> issues = new ArrayList<>();

    @ManyToOne
    private Invitation invitation;
}
