package ua.ithillel.tripplanner.model.entity;


import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<HotelBooking> bookings;
}
