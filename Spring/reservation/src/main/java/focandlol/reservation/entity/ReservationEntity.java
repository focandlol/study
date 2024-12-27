package focandlol.reservation.entity;

import focandlol.reservation.entity.auth.CustomerEntity;
import focandlol.reservation.type.ReservationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity store;

    private LocalDate date;

    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    private Integer NumOfPeople;

}
