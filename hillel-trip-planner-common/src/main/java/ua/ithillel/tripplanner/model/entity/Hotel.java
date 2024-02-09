package ua.ithillel.tripplanner.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "Hotel")
@Table(name = "t_hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HotelRoom> hotelRooms;

    public void addHotelRoom(HotelRoom hotelRoom) {
        if (hotelRooms == null) {
            hotelRooms = new ArrayList<>();
        }
        hotelRooms.add(hotelRoom);
        hotelRoom.setHotel(this);
    }
}
