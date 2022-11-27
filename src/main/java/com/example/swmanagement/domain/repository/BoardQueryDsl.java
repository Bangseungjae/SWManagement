package com.example.swmanagement.domain.repository;

import com.example.swmanagement.dto.board.BoardResponseDto;
import com.example.swmanagement.dto.board.QBoardResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.swmanagement.domain.QBoard.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardQueryDsl {

    private final JPAQueryFactory queryFactory;

    public List<BoardResponseDto> findAllBoards(Long projectId) {
        List<BoardResponseDto> dtos = queryFactory
                .select(new QBoardResponseDto(board.id, board.name, board.score, board.status, board.description))
                .from(board)
                .where(board.project.id.eq(projectId))
                .fetch();
        return dtos;
    }

    public Long sumScoreByProjectId(Long projectId) {
        Long sum = queryFactory.select(board.score.sum())
                .from(board)
                .where(board.project.id.eq(projectId))
                .fetchOne();
        if (sum == null) {
            sum = 0L;
        }
        return sum;
    }
}
