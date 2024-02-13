package ua.ithillel.tripplanner.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.entity.Hotel;
import ua.ithillel.tripplanner.repo.HotelRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

        final List<HotelDTO> allHotels = hotelService.getAllHotels();

        assertNotNull(allHotels);
        assertNotEquals(allHotels.size(), 0);
    }
}
