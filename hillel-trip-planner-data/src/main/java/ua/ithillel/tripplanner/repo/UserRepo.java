package ua.ithillel.tripplanner.repo;

import ua.ithillel.tripplanner.model.entity.User;

public interface UserRepo {
    User save(User user);
    User find(Long id);
    User remove(Long id);
}
