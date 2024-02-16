package ua.ithillel.tripplanner.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import ua.ithillel.tripplanner.model.dto.HotelBookingDTO;

import java.util.List;

@Entity(name = "HotelRoom")
@Table(name = "t_hotel_room")
@Data
@ToString(exclude = {"hotel"})
public class HotelRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "hotelRoom", fetch = FetchType.LAZY)
    private List<HotelBooking> bookings;
}
