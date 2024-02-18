package ua.ithillel.tripplanner.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.ithillel.tripplanner.exception.EntityNotFoundException;
import ua.ithillel.tripplanner.exception.InconsistencyException;
import ua.ithillel.tripplanner.model.dto.GuestDTO;
import ua.ithillel.tripplanner.model.dto.HotelBookingDTO;
import ua.ithillel.tripplanner.model.dto.HotelRoomDTO;
import ua.ithillel.tripplanner.model.dto.UserDTO;
import ua.ithillel.tripplanner.repo.HotelBookingRepo;
import ua.ithillel.tripplanner.repo.HotelRoomRepo;
import ua.ithillel.tripplanner.repo.UserRepo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class HotelBookingServiceDefaultTest extends ServiceTestParent {
    @Mock
    private UserRepo userRepoMock;
    @Mock
    private HotelRoomRepo hotelRoomRepoMock;
    @Mock
    private HotelBookingRepo hotelBookingRepoMock;

    private HotelBookingService hotelBookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        hotelBookingService = new HotelBookingServiceDefault(userRepoMock,
                hotelRoomRepoMock,
                hotelBookingMapper,
                hotelBookingRepoMock);
    }

    @Test
    public void createHotelBookingTest_success() throws InconsistencyException, EntityNotFoundException {
        HotelBookingDTO testBooking = new HotelBookingDTO();
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(1000L);
        final HotelRoomDTO hotelRoomDTO = new HotelRoomDTO();
        hotelRoomDTO.setId(12000L);
        final List<GuestDTO> guestDTOS = List.of(new GuestDTO());
        testBooking.setHotelRoom(hotelRoomDTO);
        testBooking.setUser(userDTO);
        testBooking.setGuests(guestDTOS);
        Date checkIn = new Date(LocalDate.of(LocalDate.now().getYear() + 2, 1, 1).toEpochDay());
        Date checkOut = new Date(LocalDate.of(LocalDate.now().getYear() + 2, 1, 10).toEpochDay());
        testBooking.setCheckinDate(checkIn);
        testBooking.setCheckoutDate(checkOut);

        when(userRepoMock.find(anyLong())).thenReturn(testUsers.get(0));
        when(hotelRoomRepoMock.find(anyLong())).thenReturn(testHotelRooms.get(0));
        when(hotelBookingRepoMock.findById(anyLong())).thenReturn(testHotelBookings.get(0));
        when(hotelBookingRepoMock.save(any())).thenReturn(testHotelBookings.get(2));

        final HotelBookingDTO createdBooking = hotelBookingService.createHotelBooking(testBooking);

        assertNotNull(createdBooking);
        assertNotNull(createdBooking.getId());
    }

    @Test
    public void createHotelBookingTest_throwsEntityNotFoundException() {
        HotelBookingDTO testBooking = new HotelBookingDTO();
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(1000L);
        testBooking.setUser(userDTO);

        when(userRepoMock.find(anyLong())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
           hotelBookingService.createHotelBooking(testBooking)
        );

    }

    @Test
    public void createHotelBookingTest_throwsEntityNotFoundExceptionRoom() {
        HotelBookingDTO testBooking = new HotelBookingDTO();
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(1000L);
        final HotelRoomDTO hotelRoomDTO = new HotelRoomDTO();
        hotelRoomDTO.setId(12000L);
        final List<GuestDTO> guestDTOS = List.of(new GuestDTO());
        testBooking.setHotelRoom(hotelRoomDTO);
        testBooking.setUser(userDTO);


        when(userRepoMock.find(anyLong())).thenReturn(testUsers.get(0));
        when(hotelRoomRepoMock.find(anyLong())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
                hotelBookingService.createHotelBooking(testBooking)
        );

    }

    @Test
    public void createHotelBookingTest_throwsInconsistencyExceptionGuestNum() {
        HotelBookingDTO testBooking = new HotelBookingDTO();
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(1000L);
        final HotelRoomDTO hotelRoomDTO = new HotelRoomDTO();
        hotelRoomDTO.setId(12000L);
        final List<GuestDTO> guestDTOS = Stream.generate(GuestDTO::new).limit(40).toList();
        testBooking.setHotelRoom(hotelRoomDTO);
        testBooking.setUser(userDTO);
        testBooking.setGuests(guestDTOS);


        when(userRepoMock.find(anyLong())).thenReturn(testUsers.get(0));
        when(hotelRoomRepoMock.find(anyLong())).thenReturn(testHotelRooms.get(0));

        assertThrows(InconsistencyException.class, () ->
                hotelBookingService.createHotelBooking(testBooking)
        );

    }

    @Test
    public void createHotelBookingTest_throwsInconsistencyExceptionDateOverlap() {
        HotelBookingDTO testBooking = new HotelBookingDTO();
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(1000L);
        final HotelRoomDTO hotelRoomDTO = new HotelRoomDTO();
        hotelRoomDTO.setId(12000L);
        final List<GuestDTO> guestDTOS = List.of(new GuestDTO());
        testBooking.setHotelRoom(hotelRoomDTO);
        testBooking.setUser(userDTO);
        testBooking.setGuests(guestDTOS);
        Date checkIn = testHotelBookings.get(0).getCheckinDate();
        Date checkOut = testHotelBookings.get(0).getCheckoutDate();
        testBooking.setCheckinDate(checkIn);
        testBooking.setCheckoutDate(checkOut);


        when(userRepoMock.find(anyLong())).thenReturn(testUsers.get(0));
        when(hotelRoomRepoMock.find(anyLong())).thenReturn(testHotelRooms.get(0));
        when(hotelBookingRepoMock.findById(anyLong())).thenReturn(testHotelBookings.get(0));


        assertThrows(InconsistencyException.class, () ->
                hotelBookingService.createHotelBooking(testBooking)
        );

    }
}
