package ua.ithillel.tripplanner.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = "HotelBooking")
@Table(name = "t_hotel_booking")
public class HotelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "checkin_date", nullable = false)
    private Date checkinDate;
    @Column(name = "checkout_date", nullable = false)
    private Date checkoutDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private HotelRoom hotelRoom;

    @ManyToMany
    @JoinTable(name = "t_guest_hotel_booking",
            joinColumns = @JoinColumn(name = "hotel_booking_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> guests;
}
