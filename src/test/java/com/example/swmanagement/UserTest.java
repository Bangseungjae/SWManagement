package com.example.swmanagement;

import com.example.swmanagement.domain.User;
import com.example.swmanagement.domain.repository.UserRepository;
import com.example.swmanagement.dto.LoginRequest;
import com.example.swmanagement.dto.ResponseLogin;
import com.example.swmanagement.security.service.AuthService;
import com.example.swmanagement.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("로그인 기능 테스트")
    public void signupAndLoginTest() throws Exception {
        LoginRequest userDto = LoginRequest.builder()
                .username("방승재")
                .password("1234567")
                .email("tmdwo@gmail.com")
                .build();
        userService.signUp(userDto);

        ResponseLogin authenticate = authService.authenticate(userDto.getEmail(), userDto.getPassword());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/test")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", authenticate.getAccessToken())
        )
                .andExpect(status().isOk());
    }
}
