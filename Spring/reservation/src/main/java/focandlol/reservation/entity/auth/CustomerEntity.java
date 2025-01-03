package focandlol.reservation.entity.auth;

import focandlol.reservation.entity.BaseEntity;
import focandlol.reservation.type.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * 고객 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer")
public class CustomerEntity extends BaseEntity implements BasicContent {
    /**
     * 고객 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    /**
     * 고객 이메일
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * 고객 비밀번호
     */
    @Column(nullable = false)
    private String password;

    /**
     * 고객 핸드폰 번호
     */
    @Column(nullable = false)
    private String phoneNumber;

    /**
     * 회원 구분
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
