package ua.ithillel.tripplanner.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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
}
