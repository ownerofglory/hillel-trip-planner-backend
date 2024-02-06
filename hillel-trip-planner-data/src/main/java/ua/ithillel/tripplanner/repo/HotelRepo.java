package ua.ithillel.tripplanner.repo;

import ua.ithillel.tripplanner.model.entity.Hotel;

public interface HotelRepo {
    Hotel save(Hotel hotel);
    Hotel find(Long id);
    Hotel remove(Hotel hotel);
}
