package com.example.swmanagement.dto.invitation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@Builder
@AllArgsConstructor
public class InvitationRequestDto {
    private Long projectId;

    @Email(message = "이메일 형식과 맞지 않습니다.")
    private String email;
}
