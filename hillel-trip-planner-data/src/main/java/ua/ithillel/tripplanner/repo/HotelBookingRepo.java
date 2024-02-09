package ua.ithillel.tripplanner.repo;

import ua.ithillel.tripplanner.model.entity.HotelBooking;

public interface HotelBookingRepo {
    HotelBooking save(HotelBooking hotelBooking);
    HotelBooking findById(Long id);
    HotelBooking remove(HotelBooking hotelBooking);
}
