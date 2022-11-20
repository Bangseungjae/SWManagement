package com.example.swmanagement.service;

import com.example.swmanagement.domain.Board;
import com.example.swmanagement.domain.Project;
import com.example.swmanagement.domain.repository.BoardRepository;
import com.example.swmanagement.domain.repository.ProjectRepository;
import com.example.swmanagement.dto.board.BoardRequestDto;
import com.example.swmanagement.dto.board.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final ProjectRepository projectRepository;

    public BoardResponseDto registerBoard(Long projectId, BoardRequestDto boardRequestDto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 프로젝트가 없습니다."));

        Board board = Board.builder()
                .name(boardRequestDto.getName())
                .description(boardRequestDto.getDescription())
                .score(boardRequestDto.getScore())
                .status(boardRequestDto.getPriorityStatus())
                .project(project)
                .build();

        Board save = boardRepository.save(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto(save.getId(), save.getName(), save.getScore(), save.getStatus(), save.getDescription());
        return boardResponseDto;
    }
}
