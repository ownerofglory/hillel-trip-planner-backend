package ua.ithillel.tripplanner.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ua.ithillel.tripplanner.jdbc.mapper.UserRowMapper;
import ua.ithillel.tripplanner.model.entity.*;
import ua.ithillel.tripplanner.repo.RepoTestParent;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Properties;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class HibernateTestConfig {
    @Bean
    @Scope(value = SCOPE_PROTOTYPE)
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration hiberConfig = new org.hibernate.cfg.Configuration();

        Properties props = new Properties();
        props.put("hibernate.connection.driver_class", "org.h2.Driver");
        props.put("hibernate.connection.url", "jdbc:h2:mem:tripplannertestdb");
        props.put("hibernate.connection.username", "sa");
        props.put("hibernate.connection.password", "password");
        props.put("hibernate.hbm2ddl.auto", "create");
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        hiberConfig.setProperties(props);

        hiberConfig.addAnnotatedClass(Address.class);
        hiberConfig.addAnnotatedClass(Hotel.class);
        hiberConfig.addAnnotatedClass(HotelBooking.class);
        hiberConfig.addAnnotatedClass(HotelRoom.class);
        hiberConfig.addAnnotatedClass(User.class);
        hiberConfig.addAnnotatedClass(UserRole.class);

        final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(hiberConfig.getProperties())
                .build();

        final SessionFactory sessionFactory = hiberConfig.buildSessionFactory(serviceRegistry);

        initTestData(sessionFactory);

        return sessionFactory;
    }

    @Bean
    public DataSource jdbcTemplate() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:jdbc-schema.sql")
                .addScript("classpath:jdbc-data.sql")
                .build();
    }

    @Bean
    public UserRowMapper userRowMapper() {
        return new UserRowMapper();
    }

    private void initTestData(SessionFactory sessionFactory) {
        final EntityManager entityManager = sessionFactory.createEntityManager();

        try (final InputStream inputStream = RepoTestParent.class
                .getClassLoader()
                .getResourceAsStream("data.sql");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
        ) {

            entityManager.getTransaction().begin();
            final Optional<String> scriptOpt = br.lines().reduce((acc, line) -> acc + line + "\n");

            final Query nativeQuery = entityManager.createNativeQuery(scriptOpt.get());
            nativeQuery.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (IOException e) {
            entityManager.getTransaction().rollback();
        }
    }
}
