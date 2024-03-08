package ua.ithillel.tripplanner.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import java.io.IOException;

public class HillelBasicAuthEntryPoint extends BasicAuthenticationEntryPoint {
    @Override
    public void commence(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader(HttpHeaders.WWW_AUTHENTICATE,
                "Basic realm=\"" + getRealmName() + "\"");
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("Hillel Traip Planner Realm");
    }
}
