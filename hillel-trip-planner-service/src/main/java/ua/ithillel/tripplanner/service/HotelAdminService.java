package ua.ithillel.tripplanner.service;

import ua.ithillel.tripplanner.exception.EntityNotFoundException;
import ua.ithillel.tripplanner.exception.InconsistencyException;
import ua.ithillel.tripplanner.model.dto.HotelDTO;

public interface HotelAdminService {
    HotelDTO createHotel(HotelDTO hotelDTO);
    HotelDTO updateHotel(Long id, HotelDTO hotelDTO) throws EntityNotFoundException, InconsistencyException;
    HotelDTO deleteHotel(Long id) throws EntityNotFoundException;
}
