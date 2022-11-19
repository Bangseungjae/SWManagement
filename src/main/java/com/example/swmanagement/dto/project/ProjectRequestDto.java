package com.example.swmanagement.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProjectRequestDto {
    private String title;
    private String description;
    private List<String> invitationEmail;
}
