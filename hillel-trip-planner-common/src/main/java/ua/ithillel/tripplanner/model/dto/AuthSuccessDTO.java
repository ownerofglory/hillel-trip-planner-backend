package ua.ithillel.tripplanner.model.dto;

import lombok.Data;

@Data
public class AuthSuccessDTO {
    private UserDTO user;
    private String token;
}
