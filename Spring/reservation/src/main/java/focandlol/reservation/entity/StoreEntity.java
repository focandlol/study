package focandlol.reservation.entity;

import focandlol.reservation.entity.auth.ManagerEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "store")
public class StoreEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String storeName;

    private String storePhoneNumber;

    private String location;

    private boolean cesco;

    private Integer totalSeat;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "manager_id")
    private ManagerEntity manager;
}
