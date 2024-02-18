package ua.ithillel.tripplanner.model.mapper;

import org.mapstruct.Mapper;
import ua.ithillel.tripplanner.model.dto.HotelBookingDTO;
import ua.ithillel.tripplanner.model.entity.HotelBooking;

@Mapper(componentModel = "spring")
public interface HotelBookingMapper {
    HotelBooking hotelBookingDTOToHotelBooking(HotelBookingDTO hotelBookingDTO);
    HotelBookingDTO hotelBookingToHotelBookingDTO(HotelBooking hotelBooking);
}
