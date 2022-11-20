package com.example.swmanagement.dto.board;

import com.example.swmanagement.domain.PriorityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {

    @NotNull
    @NotEmpty
    private String name;

    @Min(1)
    @Max(5)
    private Integer score;
    private PriorityStatus priorityStatus;
    private String description;
}
