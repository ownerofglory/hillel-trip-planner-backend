package ua.ithillel.tripplanner.repo;

import ua.ithillel.tripplanner.model.entity.HotelRoom;

public interface HotelRoomRepo {
    HotelRoom find(Long id);
}
