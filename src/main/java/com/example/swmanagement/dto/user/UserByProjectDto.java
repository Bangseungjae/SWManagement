package com.example.swmanagement.dto.user;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserByProjectDto {

    private Long id;
    private String username;

    @QueryProjection
    public UserByProjectDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
