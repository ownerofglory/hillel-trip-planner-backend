package ua.ithillel.tripplanner.model.entity;

import jakarta.persistence.*;
import lombok.Data;

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
}
