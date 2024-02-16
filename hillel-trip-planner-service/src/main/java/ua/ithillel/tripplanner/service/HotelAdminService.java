package ua.ithillel.tripplanner.service;

import ua.ithillel.tripplanner.model.dto.HotelDTO;

public interface HotelAdminService {
    HotelDTO createHotel(HotelDTO hotelDTO);
    HotelDTO updateHotel(Long id, HotelDTO hotelDTO);
    HotelDTO deleteHotel(Long id);
}
