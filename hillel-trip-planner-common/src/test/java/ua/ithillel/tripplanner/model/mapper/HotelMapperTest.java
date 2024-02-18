package ua.ithillel.tripplanner.model.mapper;

import org.junit.jupiter.api.Test;
import ua.ithillel.tripplanner.model.dto.HotelDTO;
import ua.ithillel.tripplanner.model.entity.Hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HotelMapperTest extends MapperTestParent {
    @Test
    public void hotelToHotelDTOTest_convertSuccessfully() {
        final Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("TEST NAME");
        hotel.setDescription("TEST DESCRIPTION");
        hotel.setPhoneNumber("TEST NUM");

        final HotelDTO hotelDTO = hotelMapper.hotelToHotelDTO(hotel);

        assertNotNull(hotelDTO);
        assertEquals(hotelDTO.getId(), hotel.getId());
        assertEquals(hotelDTO.getName(), hotel.getName());
        assertEquals(hotelDTO.getDescription(), hotel.getDescription());
        assertEquals(hotelDTO.getPhoneNumber(), hotel.getPhoneNumber());
    }
}
