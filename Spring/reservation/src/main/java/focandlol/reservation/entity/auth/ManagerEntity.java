package focandlol.reservation.entity.auth;

import focandlol.reservation.entity.BaseEntity;
import focandlol.reservation.type.UserType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "manager")
public class ManagerEntity extends BaseEntity implements BasicContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    private String username;
    private String password;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
