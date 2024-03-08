package ua.ithillel.tripplanner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.ithillel.tripplanner.auth.HillelBasicAuthEntryPoint;
import ua.ithillel.tripplanner.filter.HillelJwtFilter;
import ua.ithillel.tripplanner.repo.UserRepo;
import ua.ithillel.tripplanner.service.HillelUserDetailsService;
import ua.ithillel.tripplanner.util.JwtUtil;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // basic auth
//        http
//                .csrf(AbstractHttpConfigurer::disable) // CSRF attack
//                .authorizeHttpRequests(conf ->
//                            conf.requestMatchers(antMatcher("/auth/register")).permitAll()
//                                    .requestMatchers(antMatcher("/hotel-admin/**")).hasAuthority("ROLE_ADMIN")
//                                    .anyRequest().authenticated()
//                        )
//                .httpBasic(conf -> conf.authenticationEntryPoint(new HillelBasicAuthEntryPoint()));

        // jwt-based auth
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF attack
                .authorizeHttpRequests(conf ->
                        conf
                                .requestMatchers(antMatcher("/auth/**")).permitAll()
                                .requestMatchers(antMatcher("/hotel-admin/**")).hasAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(conf ->
                        conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        http.addFilterBefore(hillelJwtFilter(),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
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
    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User
//                .withUsername("hillel")
//                .password(bCryptPasswordEncoder().encode("Hillel123"))
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
        return new HillelUserDetailsService(userRepo);
    }
}
