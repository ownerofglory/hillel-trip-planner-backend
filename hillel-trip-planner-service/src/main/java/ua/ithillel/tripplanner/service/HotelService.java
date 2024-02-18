package ua.ithillel.tripplanner.service;

import ua.ithillel.tripplanner.exception.EntityNotFoundException;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.dto.HotelListItemDTO;
import ua.ithillel.tripplanner.model.entity.Hotel;

import java.util.List;

public interface HotelService {
    List<HotelListItemDTO> getAllHotels();
    List<HotelListItemDTO> searchHotels(int limit, int page);
    HotelDTO getHotelById(Long id) throws EntityNotFoundException;
}
