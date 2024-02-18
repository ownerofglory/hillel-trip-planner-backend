package ua.ithillel.tripplanner.repo;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ithillel.tripplanner.model.entity.HotelRoom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HotelRoomHibernateRepoTest extends RepoTestParent {
    private HotelRoomRepo hotelRoomRepo;
    private Session testSession;

    @BeforeEach
    public void setUp() {
        testSession = testSessionFactory.openSession();

        hotelRoomRepo = new HotelRoomHibernateRepo(testSession);
    }

    @Test
    public void findTest_success() {
        Long testId = 1L;

        final HotelRoom hotelRoom = hotelRoomRepo.find(testId);

        assertNotNull(hotelRoom);
        assertNotNull(hotelRoom.getId());
        assertEquals(hotelRoom.getId(), testId);
    }
}
