package ua.ithillel.tripplanner.model.dto;

import lombok.Data;

@Data
public class HotelListItemDTO {
    private Long id;
    private String name;
    private String description;
    private String phoneNumber;
}
