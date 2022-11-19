package com.example.swmanagement.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoginRequest {
    @Email
    private String email;
    @Size(min = 6)
    private String password;
    private String username;
}
