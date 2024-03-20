package ua.ithillel.tripplanner.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "User") // not a table name
@Table(name = "t_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"bookings"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "password_hash")
    private String passwordHash;
    private String provider;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Address address;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<HotelBooking> bookings;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<UserRole> roles;

    public void addRole(UserRole userRole) {
        if (roles == null) {
            roles = new ArrayList<>();
        }

        roles.add(userRole);
        userRole.setUser(this);
    }
}
