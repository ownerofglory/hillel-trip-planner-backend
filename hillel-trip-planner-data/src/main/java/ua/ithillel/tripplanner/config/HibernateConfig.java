package ua.ithillel.tripplanner.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ua.ithillel.tripplanner.model.entity.*;

import java.util.Properties;

@Configuration
@PropertySource("classpath:hibernate.properties")
public class HibernateConfig {
    @Value("${db.driver}")
    private String driverName;
    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.user}")
    private String dbUser;
    @Value("${db.password}")
    private String dbPassword;
    @Value("${db.show-sql}")
    private String showSlq;

    @Bean
    public Session session() {
        org.hibernate.cfg.Configuration hiberConfig = new org.hibernate.cfg.Configuration();

        Properties props = new Properties();
        props.put("hibernate.connection.driver_class", driverName);
        props.put("hibernate.connection.url", dbUrl);
        props.put("hibernate.connection.username", dbUser);
        props.put("hibernate.connection.password", dbPassword);
        props.put("hibernate.show_sql", showSlq);

        hiberConfig.setProperties(props);

        hiberConfig.addAnnotatedClass(Address.class);
        hiberConfig.addAnnotatedClass(Hotel.class);
        hiberConfig.addAnnotatedClass(HotelBooking.class);
        hiberConfig.addAnnotatedClass(HotelRoom.class);
        hiberConfig.addAnnotatedClass(User.class);

        final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(hiberConfig.getProperties())
                .build();

        final SessionFactory sessionFactory = hiberConfig.buildSessionFactory(serviceRegistry);

        return sessionFactory.openSession();
    }
}
