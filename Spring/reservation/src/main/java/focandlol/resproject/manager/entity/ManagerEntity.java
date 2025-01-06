package focandlol.resproject.manager.entity;

import focandlol.resproject.auth.type.UserType;
import focandlol.resproject.global.entity.BaseEntity;
import focandlol.resproject.global.entity.BasicContent;
import jakarta.persistence.*;
import lombok.*;

/**
 * 매니저 엔티티
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
     * 매니저 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    /**
     * 매니저 이메일
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * 매니저 비밀번호
     */
    @Column(nullable = false)
    private String password;

    /**
     * 매니저 핸드폰 번호
     */
    @Column(nullable = false, length = 12)
    private String phoneNumber;

    /**
     * 회원 유형
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
