package ua.ithillel.tripplanner.service;

import ua.ithillel.tripplanner.exception.EntityNotFoundException;
import ua.ithillel.tripplanner.exception.InconsistencyException;
import ua.ithillel.tripplanner.model.dto.HotelBookingDTO;
import ua.ithillel.tripplanner.model.entity.HotelBooking;

public interface HotelBookingService {
    HotelBookingDTO createHotelBooking(HotelBookingDTO booking) throws EntityNotFoundException, InconsistencyException;
}
