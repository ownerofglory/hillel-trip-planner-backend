package ua.ithillel.tripplanner.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ithillel.tripplanner.model.entity.Hotel;

import java.util.List;

public interface HotelJpaRepo extends JpaRepository<Hotel, Long> {
    List<Hotel> findAllByHotelRoomsExists();
}
