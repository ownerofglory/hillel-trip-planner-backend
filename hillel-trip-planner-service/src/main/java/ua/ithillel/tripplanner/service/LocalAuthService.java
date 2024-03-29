package ua.ithillel.tripplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.ithillel.tripplanner.exception.UserNotAuthenticatedException;
import ua.ithillel.tripplanner.model.dto.AuthSuccessDTO;
import ua.ithillel.tripplanner.model.dto.LoginDTO;
import ua.ithillel.tripplanner.model.dto.RegisterDTO;
import ua.ithillel.tripplanner.model.dto.UserDTO;
import ua.ithillel.tripplanner.model.entity.User;
import ua.ithillel.tripplanner.model.entity.UserRole;
import ua.ithillel.tripplanner.model.mapper.UserMapper;
import ua.ithillel.tripplanner.model.security.HillelUserDetails;
import ua.ithillel.tripplanner.repo.UserRepo;
import ua.ithillel.tripplanner.util.JwtUtil;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LocalAuthService implements AuthService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDTO registerUser(RegisterDTO registerDTO) throws Exception {
        List<UserRole> roles = registerDTO.getRoles().stream().map(r -> {
            UserRole userRole = new UserRole();
            userRole.setRole(r.getRole());

            return userRole;
        }).toList();

        User user = User.builder()
                .email(registerDTO.getEmail())
                .passwordHash(encoder.encode(registerDTO.getPassword()))
                .name(registerDTO.getName())
                .birthDate(new Date())
                .build();

        // assign roles
        roles.forEach(user::addRole);

        User savedUser = userRepo.save(user);

        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    public AuthSuccessDTO authenticateUser(LoginDTO loginDTO) throws Exception {
        UserDetails authUser = org.springframework.security.core.userdetails.User
                        .withUsername(loginDTO.getEmail())
                        .password(loginDTO.getPassword())
                                .build();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authUser, loginDTO.getPassword()));

        HillelUserDetails principal = (HillelUserDetails) authentication.getPrincipal();

        if (principal != null) {
            String passwordHash = principal.getPassword();
            boolean matches = encoder.matches(loginDTO.getPassword(), passwordHash);

            if (matches) {
                String token = jwtUtil.generateToken(principal);

                AuthSuccessDTO authSuccessDTO = new AuthSuccessDTO();
                authSuccessDTO.setUser(userMapper.userToUserDTO(principal.getInternalUser()));
                authSuccessDTO.setToken(token);


                return authSuccessDTO;
            }
        }

        throw new UserNotAuthenticatedException("Login data is incorrect");
    }
}
