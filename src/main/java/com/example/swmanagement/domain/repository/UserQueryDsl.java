package com.example.swmanagement.domain.repository;

import com.example.swmanagement.domain.Project;
import com.example.swmanagement.domain.QProject;
import com.example.swmanagement.domain.QUser;
import com.example.swmanagement.domain.User;
import com.example.swmanagement.dto.user.QUserByProjectDto;
import com.example.swmanagement.dto.user.UserByProjectDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.example.swmanagement.domain.QProject.project;
import static com.example.swmanagement.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserQueryDsl {
    private final JPAQueryFactory queryFactory;

    public List<UserByProjectDto> findByProjectId(Long projectId) {
        Project projectOne = queryFactory.select(project)
                .from(project)
                .leftJoin(project)
                .where(project.id.eq(projectId))
                .fetchOne();
        List<User> users = projectOne.getUser();

        List<UserByProjectDto> dtos = new ArrayList<>();
        for (User user1 : users) {
            dtos.add(new UserByProjectDto(
                    user1.getId(),
                    user1.getUsername()
            ));
        }
        return dtos;
    }
}
