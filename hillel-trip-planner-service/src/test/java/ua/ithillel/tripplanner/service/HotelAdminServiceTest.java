package ua.ithillel.tripplanner.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.ithillel.tripplanner.exception.EntityNotFoundException;
import ua.ithillel.tripplanner.exception.InconsistencyException;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.entity.Hotel;
import ua.ithillel.tripplanner.repo.HotelRepo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class HotelAdminServiceTest extends ServiceTestParent {
    @Mock
    private HotelRepo hotelRepoMock;

    private HotelAdminService hotelAdminService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        hotelAdminService = new HotelAdminServiceDefault(hotelRepoMock, hotelMapper);
    }

    @Test
    public void createHotelTest_success() {
        final HotelDTO newHotel = new HotelDTO();
        newHotel.setName("Test");
        newHotel.setDescription("Test");
        newHotel.setPhoneNumber("+1000000");

        when(hotelRepoMock.save(any())).thenReturn(testHotels.get(0));

        final HotelDTO created = hotelAdminService.createHotel(newHotel);

        assertNotNull(created);
        assertNotNull(created.getId());
    }

    @Test
    public void updateHotelTest_success() throws InconsistencyException, EntityNotFoundException {
        final Hotel mockHotel = testHotels.get(0);

        final HotelDTO existing = new HotelDTO();
        existing.setId(mockHotel.getId());
        existing.setName(mockHotel.getName());
        existing.setDescription(mockHotel.getDescription());
        existing.setPhoneNumber(mockHotel.getPhoneNumber());

        when(hotelRepoMock.find(anyLong())).thenReturn(mockHotel);
        when(hotelRepoMock.save(any())).thenReturn(mockHotel);

        final HotelDTO updated = hotelAdminService.updateHotel(mockHotel.getId(), existing);

        assertNotNull(updated);
    }

    @Test
    public void updateHotelTest_throwsEntityNotFoundException() {
        Long testId = 9999999L;

        final HotelDTO existing = new HotelDTO();
        existing.setId(testId);
        existing.setName("TEST");
        existing.setDescription("mockHotel.getDescription()");
        existing.setPhoneNumber("mockHotel.getPhoneNumber()");

        when(hotelRepoMock.find(anyLong())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> hotelAdminService.updateHotel(testId, existing));
    }

    @Test
    public void updateHotelTest_throwsInconsistencyException() {
        Long testId = 9999999L;

        final HotelDTO existing = new HotelDTO();
        existing.setId(888888L);
        existing.setName("TEST");
        existing.setDescription("mockHotel.getDescription()");
        existing.setPhoneNumber("mockHotel.getPhoneNumber()");

        assertThrows(InconsistencyException.class, () -> hotelAdminService.updateHotel(testId, existing));
    }

    @Test
    public void deleteHotelTest_success() throws EntityNotFoundException {
        Long testId = 1000L;

        when(hotelRepoMock.find(anyLong())).thenReturn(testHotels.get(0));
        when(hotelRepoMock.remove(any())).thenReturn(testHotels.get(0));

        final HotelDTO deleted = hotelAdminService.deleteHotel(testId);

        assertNotNull(deleted);
        assertNotNull(deleted.getId());
    }

    @Test
    public void deleteHotelTest_throwsEntityNotFoundException(){
        Long testId = 1000L;

        when(hotelRepoMock.find(anyLong())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> hotelAdminService.deleteHotel(testId));
    }
}
