package ua.ithillel.tripplanner.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelDTO {
    private Long id;
    private String name;
    private String description;
    private String phoneNumber;

    private List<HotelRoomDTO> hotelRooms;
}
