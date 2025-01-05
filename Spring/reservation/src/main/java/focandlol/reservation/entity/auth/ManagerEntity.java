package focandlol.reservation.entity.auth;

import focandlol.reservation.entity.BaseEntity;
import focandlol.reservation.type.UserType;
import jakarta.persistence.*;
import lombok.*;

/**
 * 가게 주인 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "manager")
public class ManagerEntity extends BaseEntity implements BasicContent {

    /**
     * 가게 주인 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    /**
     * 가게 주인 이메일
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * 가게 주인 비밀번호
     */
    @Column(nullable = false)
    private String password;

    /**
     * 가게 주인 핸드폰 번호
     */
    @Column(nullable = false, length = 12)
    private String phoneNumber;

    /**
     * 회원 구분
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
