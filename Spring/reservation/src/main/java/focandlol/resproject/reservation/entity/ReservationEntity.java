package focandlol.resproject.reservation.entity;

import focandlol.resproject.customer.entity.CustomerEntity;
import focandlol.resproject.global.entity.BaseEntity;
import focandlol.resproject.reservation.type.ReservationType;
import focandlol.resproject.store.entity.StoreEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 예약 엔티티 클래스
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservation")
public class ReservationEntity extends BaseEntity {

    /**
     * 예약 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    /**
     * 예약한 고객
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    /**
     * 예약 가게
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StoreEntity store;

    /**
     * 예약 날짜
     */
    @Column(nullable = false)
    private LocalDate date;

    /**
     * 예약 시간
     */
    @Column(nullable = false)
    private LocalTime time;

    /**
     * 예약 상태
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationType reservationType;

    /**
     * 예약 인원수
     */
    @Column(nullable = false)
    private Integer numOfPeople;

    /**
     * locaDateTime 만드는 메소드
     *
     * @return LocalDateTime
     */
    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.of(date, time);
    }

}
