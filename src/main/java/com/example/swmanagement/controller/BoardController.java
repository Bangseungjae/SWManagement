package com.example.swmanagement.controller;

import com.example.swmanagement.domain.Board;
import com.example.swmanagement.dto.board.BoardRequestDto;
import com.example.swmanagement.dto.board.BoardResponseDto;
import com.example.swmanagement.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = "스토리보드 컨트롤러")
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "스토리보드 등록, id에는 project id 등록")
    @PostMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> registerBoard(@RequestBody @Valid BoardRequestDto dto, @PathVariable("id") Long projectId) {
        BoardResponseDto board = boardService.registerBoard(projectId, dto);

        return ResponseEntity.ok().body(board);
    }
}
