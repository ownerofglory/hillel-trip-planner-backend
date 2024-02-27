package ua.ithillel.tripplanner.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ithillel.tripplanner.model.entity.HotelRoom;

import java.util.List;

public interface HotelRoomJpaRepo extends JpaRepository<HotelRoom, Long> {
    List<HotelRoom> findAllByCapacity(int capacity);
}
