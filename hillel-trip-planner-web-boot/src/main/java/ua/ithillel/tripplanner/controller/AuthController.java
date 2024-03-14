package ua.ithillel.tripplanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.tripplanner.model.dto.AuthSuccessDTO;
import ua.ithillel.tripplanner.model.dto.LoginDTO;
import ua.ithillel.tripplanner.model.dto.RegisterDTO;
import ua.ithillel.tripplanner.model.dto.UserDTO;
import ua.ithillel.tripplanner.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final String X_JWT_TOKEN_HEADER = "x-jwt-token";

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterDTO registerDTO) throws Exception {
        UserDTO userDTO = authService.registerUser(registerDTO);

        return ResponseEntity.ok(userDTO);
    }


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) throws Exception {
        AuthSuccessDTO authSuccess = authService.authenticateUser(loginDTO);


        return ResponseEntity.ok()
                .header(X_JWT_TOKEN_HEADER, authSuccess.getToken())
                .body(authSuccess.getUser());
    }
}
