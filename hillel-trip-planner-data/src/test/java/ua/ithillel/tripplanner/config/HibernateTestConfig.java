package ua.ithillel.tripplanner.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.ithillel.tripplanner.model.entity.*;

import java.util.Properties;

public class HibernateTestConfig {
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

        final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(hiberConfig.getProperties())
                .build();

        return hiberConfig.buildSessionFactory(serviceRegistry);
    }
}
