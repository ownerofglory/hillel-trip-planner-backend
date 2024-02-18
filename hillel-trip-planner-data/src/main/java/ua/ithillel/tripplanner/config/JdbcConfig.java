package ua.ithillel.tripplanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:jdbc-config.properties")
public class JdbcConfig {
    @Value("${mysql.driver-name}")
    private String driverClass;
    @Value("${mysql.url}")
    private String dbUrl;
    @Value("${mysql.user}")
    private String dbUser;
    @Value("${mysql.password}")
    private String dbPassword;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName(driverClass);
        datasource.setUsername(dbUser);
        datasource.setPassword(dbPassword);
        datasource.setUrl(dbUrl);

        return new JdbcTemplate(datasource);
    }
}
