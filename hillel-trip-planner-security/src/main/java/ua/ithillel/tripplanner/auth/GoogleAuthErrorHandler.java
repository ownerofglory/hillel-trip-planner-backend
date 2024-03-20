package ua.ithillel.tripplanner.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import ua.ithillel.tripplanner.repo.UserRepo;
import ua.ithillel.tripplanner.util.JwtUtil;

import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class GoogleAuthErrorHandler implements AuthenticationFailureHandler {
    private final static String ERROR_REDIRECT_URL = System.getenv("AUTH_ERROR_REDIRECT_URL");

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.sendRedirect(ERROR_REDIRECT_URL);
    }
}
