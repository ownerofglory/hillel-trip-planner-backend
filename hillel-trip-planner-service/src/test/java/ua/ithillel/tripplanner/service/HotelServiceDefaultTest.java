package ua.ithillel.tripplanner.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.ithillel.tripplanner.exception.EntityNotFoundException;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.dto.HotelListItemDTO;
import ua.ithillel.tripplanner.repo.HotelRepo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class HotelServiceDefaultTest extends ServiceTestParent {
    @Mock
    private HotelRepo hotelRepoMock;

    private HotelService hotelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        hotelService = new HotelServiceDefault(hotelRepoMock, hotelMapper);
    }

    @Test
    public void getAllHotelsTest_success() {
        when(hotelRepoMock.findAll()).thenReturn(testHotels);

        final List<HotelListItemDTO> allHotels = hotelService.getAllHotels();

        assertNotNull(allHotels);
        assertNotEquals(allHotels.size(), 0);
    }

    @Test
    public void searchHotelsTest_success() {
        int testLimit = 100;
        int testPage = 1;

        when(hotelRepoMock.findAll()).thenReturn(testHotels);

        final List<HotelListItemDTO> hotels = hotelService.searchHotels(100, 1);

        assertNotNull(hotels);
        assertNotEquals(hotels.size(), 0);
        assertTrue(hotels.size() <= testLimit);
    }

    @Test
    public void getHotelByIdTest_success() throws EntityNotFoundException {
        Long testId = 1L;

        when(hotelRepoMock.find(anyLong())).thenReturn(testHotels.get(0));

        final HotelDTO hotelById = hotelService.getHotelById(testId);

        assertNotNull(hotelById);
        assertNotNull(hotelById.getId());
    }

    @Test
    public void getHotelByIdTest_throwsEntityNotFoundException() {
        Long testId = 1L;

        when(hotelRepoMock.find(anyLong())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> hotelService.getHotelById(testId));
    }
}
