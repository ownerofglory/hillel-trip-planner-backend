package ua.ithillel.tripplanner.repo;

import ua.ithillel.tripplanner.model.entity.Hotel;

import java.util.List;

public interface HotelRepo {
    Hotel save(Hotel hotel);
    Hotel find(Long id);
    List<Hotel> findAll();
    Hotel remove(Hotel hotel);
}
