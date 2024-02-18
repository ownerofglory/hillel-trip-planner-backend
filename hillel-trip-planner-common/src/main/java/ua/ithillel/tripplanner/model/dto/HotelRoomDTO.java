package ua.ithillel.tripplanner.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelRoomDTO {
    private Long id;
    private int capacity;
    private double price;
}
