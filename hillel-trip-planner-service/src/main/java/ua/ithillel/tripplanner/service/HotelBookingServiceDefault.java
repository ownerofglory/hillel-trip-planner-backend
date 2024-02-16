package ua.ithillel.tripplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ithillel.tripplanner.exception.EntityNotFoundException;
import ua.ithillel.tripplanner.exception.InconsistencyException;
import ua.ithillel.tripplanner.model.dto.HotelBookingDTO;
import ua.ithillel.tripplanner.model.dto.HotelRoomDTO;
import ua.ithillel.tripplanner.model.dto.UserDTO;
import ua.ithillel.tripplanner.model.entity.HotelBooking;
import ua.ithillel.tripplanner.model.entity.HotelRoom;
import ua.ithillel.tripplanner.model.entity.User;
import ua.ithillel.tripplanner.model.mapper.HotelBookingMapper;
import ua.ithillel.tripplanner.repo.HotelBookingRepo;
import ua.ithillel.tripplanner.repo.HotelRoomRepo;
import ua.ithillel.tripplanner.repo.UserRepo;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelBookingServiceDefault implements HotelBookingService {
    private final UserRepo userRepo;
    private final HotelRoomRepo hotelRoomRepo;
    private final HotelBookingMapper hotelBookingMapper;
    private final HotelBookingRepo hotelBookingRepo;

    @Override
    public HotelBookingDTO createHotelBooking(HotelBookingDTO booking) throws EntityNotFoundException, InconsistencyException {
        final UserDTO user = booking.getUser();
        // check if user exists
        final User exitingUser = userRepo.find(user.getId());
        if (exitingUser == null) {
            throw new EntityNotFoundException("User with given id doesn't exist");
        }

        // check if room exists
        final HotelRoomDTO hotelRoom = booking.getHotelRoom();
        final HotelRoom existingRoom = hotelRoomRepo.find(hotelRoom.getId());
        if (existingRoom == null) {
            throw new EntityNotFoundException("Room with given id doesn't exist");
        }
        // check capacity - number of guests
        final int capacity = existingRoom.getCapacity();
        final int requiredCapacity = booking.getGuests().size() + 1;
        if (requiredCapacity > capacity) {
            throw new InconsistencyException("Required capacity is bigger than available by the room");
        }

        // check availability by date
        final Date checkinDate = booking.getCheckinDate();
        final Date checkoutDate = booking.getCheckoutDate();
        final List<HotelBooking> bookings = existingRoom.getBookings();
        for (HotelBooking existingBooking:
                bookings) {
            final Date exCheckin = existingBooking.getCheckinDate();
            final Date exCheckout = existingBooking.getCheckoutDate();
            // TODO: check date
        }

        // map object
        final HotelBooking hotelBooking = hotelBookingMapper.hotelBookingDTOToHotelBooking(booking);


        // persist
        final HotelBooking savedBooking = hotelBookingRepo.save(hotelBooking);

        return hotelBookingMapper.hotelBookingToHotelBookingDTO(savedBooking);
    }
}
