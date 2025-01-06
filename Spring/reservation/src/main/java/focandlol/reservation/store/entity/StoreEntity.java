package focandlol.reservation.store.entity;

import focandlol.reservation.global.entity.BaseEntity;
import focandlol.reservation.manager.entity.ManagerEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

/**
 * 가게 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "store")
public class StoreEntity extends BaseEntity {

    /**
     * 가게 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    /**
     * 가게 이름
     */
    @Column(nullable = false, length = 50)
    private String storeName;

    /**
     * 가게 전화번호
     */
    @Column(nullable = false, length = 12)
    private String storePhoneNumber;

    /**
     * 가게 설명
     */
    @Column(length = 300)
    private String description;

    /**
     * 가게 위치
     */
    @Column(nullable = false)
    private String location;

    /**
     * 세스코 사용 여부
     */
    @Column(nullable = false)
    private boolean cesco;

    /**
     * 별점
     */
    @Column(precision = 2, scale = 1, nullable = false)
    @Digits(integer = 1, fraction = 1)
    @Min(0)
    @Max(5)
    private BigDecimal star;

    /**
     * 가게 총 예약 가능 인원
     */
    @Column(nullable = false)
    private Integer totalSeat;

    /**
     * 가게 주인
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ManagerEntity manager;

    /**
     * 가게 처음 만들었을 때 별점 기본 값
     */
    @PrePersist
    public void prePersist() {
        if (star == null) {
            star = BigDecimal.ZERO;
        }
    }
}
