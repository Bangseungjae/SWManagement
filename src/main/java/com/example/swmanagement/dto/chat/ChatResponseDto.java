package com.example.swmanagement.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponseDto {

    private Long id;
    private String content;
}
