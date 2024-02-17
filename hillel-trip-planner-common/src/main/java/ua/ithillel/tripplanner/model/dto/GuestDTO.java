package ua.ithillel.tripplanner.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GuestDTO {
    private Long id;
    private String name;
    private String email;
}
