package ua.ithillel.tripplanner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ua.ithillel.tripplanner.auth.GoogleAuthErrorHandler;
import ua.ithillel.tripplanner.auth.GoogleAuthSuccessHandler;
import ua.ithillel.tripplanner.auth.JwtAuthEntryPoint;
import ua.ithillel.tripplanner.client.GoogleAccessResponseClient;
import ua.ithillel.tripplanner.filter.HillelJwtFilter;
import ua.ithillel.tripplanner.repo.UserRepo;
import ua.ithillel.tripplanner.service.GoogleOAuth2UserService;
import ua.ithillel.tripplanner.service.GoogleOidcUserService;
import ua.ithillel.tripplanner.service.HillelUserDetailsService;
import ua.ithillel.tripplanner.util.JwtUtil;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;
    @Autowired
    private GoogleAuthSuccessHandler googleAuthSuccessHandler;
    @Autowired
    private GoogleAuthErrorHandler googleAuthErrorHandler;
    @Autowired
    private GoogleAccessResponseClient googleAccessResponseClient;
    @Autowired
    private GoogleOidcUserService googleOidcUserService;
    @Autowired
    private GoogleOAuth2UserService googleOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // jwt-based auth
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(conf ->
                        conf
                                .requestMatchers(antMatcher("/auth/**")).permitAll()
                                .requestMatchers(antMatcher("/actuator/**")).permitAll()
                                .requestMatchers(antMatcher("/hotel-admin/**")).hasAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated()
                )
                .exceptionHandling(conf ->
                        conf.authenticationEntryPoint(jwtAuthEntryPoint))
                .sessionManagement(conf ->
                        conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2Login(conf ->
                        conf
                                .authorizationEndpoint(end ->
                                        end.baseUri("/auth/oauth2"))

                                .redirectionEndpoint(redirect ->
                                        redirect.baseUri("/auth/google/callback"))

                                .successHandler(googleAuthSuccessHandler)
                                .failureHandler(googleAuthErrorHandler)

                                .tokenEndpoint(token ->
                                        token.accessTokenResponseClient(googleAccessResponseClient))

                                .userInfoEndpoint(user ->
                                        user
                                                .userService(googleOAuth2UserService)
                                                .oidcUserService(googleOidcUserService)));

        http.addFilterBefore(hillelJwtFilter(),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedHeaders(List.of(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "DELETE", "PUT", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HillelJwtFilter hillelJwtFilter() {
        return new HillelJwtFilter(jwtUtil, userDetailsService());
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new HillelUserDetailsService(userRepo);
    }
}
