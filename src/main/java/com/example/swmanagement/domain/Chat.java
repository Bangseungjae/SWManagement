package com.example.swmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Issue issue;

    @ManyToOne
    private User user;

    @Column
    private String content;
}
