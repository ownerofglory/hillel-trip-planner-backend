package ua.ithillel.tripplanner.service;

import ua.ithillel.tripplanner.model.dto.AuthSuccessDTO;
import ua.ithillel.tripplanner.model.dto.LoginDTO;
import ua.ithillel.tripplanner.model.dto.RegisterDTO;
import ua.ithillel.tripplanner.model.dto.UserDTO;
import ua.ithillel.tripplanner.model.entity.User;

public interface AuthService {
    UserDTO registerUser(RegisterDTO registerDTO) throws Exception;
    AuthSuccessDTO authenticateUser(LoginDTO loginDTO) throws Exception;

}
