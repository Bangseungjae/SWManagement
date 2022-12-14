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
public class User extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToMany(mappedBy = "user")
    @Builder.Default
    private List<Project> projects = new ArrayList<>();

    @Builder.Default
    @OneToMany
    private List<Issue> issues = new ArrayList<>();

    @Builder.Default
    @OneToMany
    private List<Chat> chats = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Invitation invitation;

}
