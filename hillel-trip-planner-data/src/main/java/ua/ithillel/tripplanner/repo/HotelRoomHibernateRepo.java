package ua.ithillel.tripplanner.repo;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ua.ithillel.tripplanner.model.entity.HotelRoom;

@RequiredArgsConstructor
@Repository
public class HotelRoomHibernateRepo implements HotelRoomRepo {
    private final Session session;

    @Override
    public HotelRoom find(Long id) {
        return session.find(HotelRoom.class, id);
    }
}
