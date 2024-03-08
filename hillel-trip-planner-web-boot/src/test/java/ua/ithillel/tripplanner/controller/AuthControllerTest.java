package ua.ithillel.tripplanner.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.ithillel.tripplanner.model.dto.*;
import ua.ithillel.tripplanner.service.AuthService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthControllerTest extends ControllerTestParent {
    @Mock
    private AuthService authServiceMock;
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        authController = new AuthController(authServiceMock);

        this.mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(globalExceptionHandler)
                .build();
    }

    @Test
    public void registerTest_ok() throws Exception {
        when(authServiceMock.registerUser(any())).thenReturn(testUsers.get(0));

        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail("test@test.com");
        registerDTO.setName("Test");
        registerDTO.setRoles(List.of(new UserRoleDTO(){{setRole("ROLE_USER");}}));

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void loginTest_ok() throws Exception {
        String testToken = "-----";
        UserDTO testUser = testUsers.get(0);

        AuthSuccessDTO authSuccessDTO = new AuthSuccessDTO();
        authSuccessDTO.setToken(testToken);
        authSuccessDTO.setUser(testUser);

        when(authServiceMock.authenticateUser(any())).thenReturn(authSuccessDTO);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@test.com");
        loginDTO.setPassword("Test");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("x-jwt-token", testToken))
                .andExpect(jsonPath("$.id").value(testUser.getId()));
    }

    @Test
    public void loginTest_unauthorized() throws Exception {
        when(authServiceMock.authenticateUser(any())).thenThrow(BadCredentialsException.class);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("hhhhhhhhh");
        loginDTO.setPassword("hhhhhhhh");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isUnauthorized());
    }
}
