package focandlol.mfa.datas.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@Table(name="users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(nullable = false)
    private long id;

    @Column(length = 30)
    private String username;

    @Column(length = 512)
    private String password;

    @Column(length = 1000)
    private String roles;
}
