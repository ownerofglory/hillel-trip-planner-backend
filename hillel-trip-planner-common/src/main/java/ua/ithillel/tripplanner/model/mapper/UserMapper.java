package ua.ithillel.tripplanner.model.mapper;

import org.mapstruct.Mapper;
import ua.ithillel.tripplanner.model.dto.UserDTO;
import ua.ithillel.tripplanner.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}
