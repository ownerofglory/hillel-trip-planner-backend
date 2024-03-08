package ua.ithillel.tripplanner.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegisterDTO {
    private String email;
    private String name;
    private String password;
    private List<UserRoleDTO> roles;
}
