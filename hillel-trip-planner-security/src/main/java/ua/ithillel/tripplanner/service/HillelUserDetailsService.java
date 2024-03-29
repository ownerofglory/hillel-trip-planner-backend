package ua.ithillel.tripplanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.ithillel.tripplanner.model.entity.User;
import ua.ithillel.tripplanner.model.entity.UserRole;
import ua.ithillel.tripplanner.model.security.HillelUserDetails;
import ua.ithillel.tripplanner.repo.UserRepo;

import java.util.List;

@RequiredArgsConstructor
public class HillelUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byEmail = userRepo.findByEmail(username);
        if (byEmail == null) {
            throw new UsernameNotFoundException(username);
        }

        return new HillelUserDetails(byEmail);
    }
}
