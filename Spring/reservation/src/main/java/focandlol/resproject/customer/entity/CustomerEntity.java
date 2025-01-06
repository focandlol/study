package focandlol.resproject.customer.entity;

import focandlol.resproject.auth.type.UserType;
import focandlol.resproject.global.entity.BaseEntity;
import focandlol.resproject.global.entity.BasicContent;
import jakarta.persistence.*;
import lombok.*;

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
     * 암호화 되어서 저장
     */
    @Column(nullable = false)
    private String password;

    /**
     * 고객 핸드폰 번호
     */
    @Column(nullable = false, length = 12)
    private String phoneNumber;

    /**
     * 회원 구분
     * CUSTOMER
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
