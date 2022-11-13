package com.example.swmanagement.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany
    private List<Project> projects = new ArrayList<>();

    @OneToMany
    private List<Issue> issues = new ArrayList<>();

    @OneToMany
    private List<Chat> chats = new ArrayList<>();

}
