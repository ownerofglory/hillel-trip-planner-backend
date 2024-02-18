package ua.ithillel.tripplanner.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelBookingDTO {
    private Long id;
    private Date checkinDate;
    private Date checkoutDate;
    private UserDTO user;
    private HotelRoomDTO hotelRoom;
    private List<GuestDTO> guests;
}
