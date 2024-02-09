package ua.ithillel.tripplanner.model.dto;

import lombok.Data;

@Data
public class HotelRoomDTO {
    private Long id;
    private int capacity;
    private double price;
}
