package ua.ithillel.tripplanner.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "t_user_role")
@ToString(exclude = {"user"})
@NoArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserRole(String role) {
        this.role = role;
    }
}
