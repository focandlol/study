package focandlol.resproject.review.entity;

import focandlol.resproject.customer.entity.CustomerEntity;
import focandlol.resproject.global.entity.BaseEntity;
import focandlol.resproject.reservation.entity.ReservationEntity;
import focandlol.resproject.store.entity.StoreEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

/**
 * 리뷰 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "review")
public class ReviewEntity extends BaseEntity {

    /**
     * 리뷰 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    /**
     * 리뷰 작성 고객
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CustomerEntity customer;

    /**
     * 리뷰 작성할 가게
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StoreEntity store;

    /**
     * 어떤 예약으로 리뷰 쓰는지
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private ReservationEntity reservation;

    /**
     * 리뷰 내용
     */
    @Column(length = 300)
    private String content;

    /**
     * 별점
     */
    @Column(precision = 2, scale = 1, nullable = false)
    @Digits(integer = 1, fraction = 1)
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private BigDecimal star;
}
