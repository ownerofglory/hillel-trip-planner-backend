package ua.ithillel.tripplanner.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ua.ithillel.tripplanner.model.entity.User;
import ua.ithillel.tripplanner.model.entity.UserRole;
import ua.ithillel.tripplanner.model.security.HillelUserDetails;
import ua.ithillel.tripplanner.repo.UserRepo;
import ua.ithillel.tripplanner.util.JwtUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class GoogleAuthSuccessHandler implements AuthenticationSuccessHandler {
    private final static String SUCCESS_REDIRECT_URL = System.getenv("AUTH_SUCCESS_REDIRECT_URL");

    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
                                        HttpServletResponse response, 
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        String email = principal.getAttribute("email");
        String fullName = principal.getAttribute("name");
        User user;

        try {
            user = userRepo.findByEmail(email);

        } catch (Exception e) {
            user = User.builder()
                    .roles(principal.getAuthorities()
                            .stream()
                            .map(a -> new UserRole(a.getAuthority()))
                            .toList())
                    .name(fullName)
                    .email(email)
                    .birthDate(new Date(System.currentTimeMillis()))
                    .provider("google")
                    .roles(List.of(new UserRole("ROLE_USER")))
                    .build();

            User savedUser = userRepo.save(user);

            String token = jwtUtil.generateToken(new HillelUserDetails(savedUser));

            response.sendRedirect(SUCCESS_REDIRECT_URL + "?jwt=" + token);
        }
    }
}
