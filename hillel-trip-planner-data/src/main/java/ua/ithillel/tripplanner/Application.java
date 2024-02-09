package ua.ithillel.tripplanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ua.ithillel.tripplanner.model.entity.*;
import ua.ithillel.tripplanner.repo.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.configure("hibernate-annotation.cfg.xml");

        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Hotel.class);
        configuration.addAnnotatedClass(HotelRoom.class);
        configuration.addAnnotatedClass(HotelBooking.class);
        configuration.addAnnotatedClass(User.class);

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();


        try(
                final SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

                final Session session = sessionFactory.openSession();

                final EntityManagerFactory entityManagerFactory
                    = Persistence.createEntityManagerFactory("tripplannerpersistence");

            EntityManager entityManager = entityManagerFactory.createEntityManager();
        ) {


//            HotelRepo hotelRepo = new HotelHibernateSessionRepo(session);
//
//            final Hotel hotel = new Hotel();
//            hotel.setName("Some hotel");
//            hotel.setDescription("Some hotel description");
//            hotel.setPhoneNumber("+1111111");
//
//            final HotelRoom hotelRoom = new HotelRoom();
//            hotelRoom.setCapacity(4);
//            hotelRoom.setPrice(100);
//
//            hotel.addHotelRoom(hotelRoom);
//
//            final Hotel savedHotel = hotelRepo.save(hotel);


//            final User user1 = new User();
//            entityManager.persist(user1);
//
//            final Address address = new Address();
//            user1.setAddress(address);


//            entityManager.getTransaction().begin();
//
//            final HotelBooking hotelBooking = entityManager.find(HotelBooking.class, 2);
//
//
////            final User someUser = entityManager.find(User.class, 18);
////
////            entityManager.remove(someUser);
//
////
////            final Hotel hotel = entityManager.find(Hotel.class, 1);
////
////            final List<HotelRoom> hotelRooms = hotel.getHotelRooms();
//
//
//            entityManager.getTransaction().commit();




//            UserRepo userRepo = new UserMySqlJpaRepo(entityManager);
            UserRepo userRepo = new UserHibernateSessionRepo(session);
            HotelRepo hotelRepo = new HotelHibernateSessionRepo(session);
            HotelBookingRepo hotelBookingRepo = new HotelBookingHibernateSessionRepo(session);

            final List<Hotel> all = hotelRepo.findAll();


            final User byEmail = userRepo.findByEmail("dmytroivanov@example.com");




            final Hotel hotel = hotelRepo.find(1L);

            final HotelBooking hotelBooking = new HotelBooking();
            final HotelRoom hotelRoom = hotel.getHotelRooms().get(0);
            hotelBooking.setHotelRoom(hotelRoom);
            hotelBooking.setUser(byEmail);
            hotelBooking.setCheckinDate(new Date());

            final LocalDateTime localDateTime = LocalDateTime.of(2024, 02, 23, 12, 01, 01);

            hotelBooking.setCheckoutDate(Date.from(localDateTime.toInstant(ZoneOffset.UTC)));

            final HotelBooking savedBooking = hotelBookingRepo.save(hotelBooking);


            final User user = userRepo.find(2L);

            final User testUser = User.builder().email("new2@sdfds.com")
                    .birthDate(new Date())
                    .name("New2 User2")
                    .build();


//            final User savedUser = userRepo.save(testUser);

            System.out.println();


        }
    }
}
