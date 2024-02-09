package ua.ithillel.tripplanner.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ithillel.tripplanner.config.HibernateTestConfig;
import ua.ithillel.tripplanner.model.entity.HotelBooking;
import ua.ithillel.tripplanner.model.entity.HotelRoom;
import ua.ithillel.tripplanner.model.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HotelBookingHibernateSessionRepoTest {
    private HotelBookingRepo hotelBookingRepo;
    private SessionFactory testSessionFactory;
    private Session testSession;

    @BeforeEach
    public void setUp() {
        testSessionFactory = new HibernateTestConfig().sessionFactory();
        testSession = testSessionFactory.openSession();

        initTestData();

        hotelBookingRepo = new HotelBookingHibernateSessionRepo(testSession);
    }

    @Test
    public void saveTest_returnsNonNullBooking() {
        final HotelBooking existingBooking = hotelBookingRepo.findById(1L);
        final HotelRoom hotelRoom = existingBooking.getHotelRoom();
        final User existingUser = existingBooking.getUser();

        final Date checkoutDate = Date.from(LocalDateTime.of(LocalDateTime.now().getYear() + 1, 1, 12, 0, 0, 0)
                .toInstant(ZoneOffset.UTC));

        final HotelBooking hotelBooking = new HotelBooking();
        hotelBooking.setHotelRoom(hotelRoom);
        hotelBooking.setUser(existingUser);
        hotelBooking.setCheckinDate(new Date());
        hotelBooking.setCheckoutDate(checkoutDate);

        final HotelBooking savedBooking = hotelBookingRepo.save(hotelBooking);

        assertNotNull(savedBooking);
        assertNotNull(savedBooking.getId());
        assertEquals(savedBooking.getHotelRoom(), hotelRoom);
        assertEquals(savedBooking.getUser(), existingUser);
    }

    @Test
    public void findByIdTest_returnsNonNullBooking() {
        Long testId = 1L;

        final HotelBooking byId = hotelBookingRepo.findById(testId);

        assertNotNull(byId);
        assertNotNull(byId.getId());
        assertEquals(byId.getId(), testId);
    }

    @Test
    public void removeTest() {
        Long testId = 1L;

        final HotelBooking existing = hotelBookingRepo.findById(testId);

        final HotelBooking removed = hotelBookingRepo.remove(existing);

        assertNotNull(removed);
        assertNotNull(removed.getId());
        assertEquals(removed.getId(), existing.getId());
    }
    
    @AfterEach
    public void tearDown() {
        testSession.close();
        testSessionFactory.close();
    }

    private void initTestData() {
        if (testSession != null) {
            final EntityManager entityManager = testSessionFactory.createEntityManager();

            try (final InputStream inputStream = getClass()
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
}
