package ua.ithillel.tripplanner.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ithillel.tripplanner.config.HibernateTestConfig;
import ua.ithillel.tripplanner.model.entity.Hotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class HotelHibernateSessionRepoTest {
    private HotelRepo hotelRepo;
    private SessionFactory testSessionFactory;
    private Session testSession;

    @BeforeEach
    public void setUp() {
        testSessionFactory = new HibernateTestConfig().sessionFactory();
        testSession = testSessionFactory.openSession();

        initTestData();

        hotelRepo = new HotelHibernateSessionRepo(testSession);
    }

    @Test
    public void saveTest_returnsNonNullHotel() {
        final Hotel hotel = new Hotel();
        hotel.setName("TEST NAME");
        hotel.setDescription("TEST DESCRIPTION");
        hotel.setPhoneNumber("TEST NUM");

        final Hotel savedHotel = hotelRepo.save(hotel);

        assertNotNull(savedHotel);
        assertNotNull(savedHotel.getId());
        assertEquals(savedHotel.getName(), hotel.getName());
    }

    @Test
    public void findTest_returnsNonNullHotel() {
        Long testId = 5L;

        final Hotel hotel = hotelRepo.find(testId);

        assertNotNull(hotel);
        assertNotNull(hotel.getId());
        assertEquals(hotel.getId(), testId);
    }

    @Test
    public void findAllTest_returnsNonEmptyHotelList() {
        final List<Hotel> hotels = hotelRepo.findAll();

        assertNotNull(hotels);
        assertNotEquals(hotels.size(), 0);
    }

    @Test
    public void removeTest_removesExistingHotel() {
        Long testId = 5L;

        final Hotel existing = hotelRepo.find(testId);

        final Hotel removed = hotelRepo.remove(existing);

        assertNotNull(removed);
        assertEquals(removed.getId(), testId);
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
