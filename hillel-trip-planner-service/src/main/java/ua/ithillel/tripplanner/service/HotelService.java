package ua.ithillel.tripplanner.service;

import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.entity.Hotel;

import java.util.List;

public interface HotelService {
    List<HotelDTO> getAllHotels();
}
