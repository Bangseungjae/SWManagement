package com.example.swmanagement.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProjectRequestDto {

    @NotEmpty
    private String title;
    private String description;
    private List<String> invitationEmail;
}
