package ua.ithillel.tripplanner.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Date birthDate;
}
