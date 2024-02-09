package ua.ithillel.tripplanner.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class HotelDTO {
    private Long id;
    private String name;
    private String description;
    private String phoneNumber;

    private List<HotelRoomDTO> hotelRooms;
}
