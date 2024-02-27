package ua.ithillel.tripplanner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ua.ithillel.tripplanner")
public class SpringDataJpaConfig {
}
