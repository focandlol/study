package focandlol.mfa.datas.entity;

import focandlol.mfa.datas.dto.MfaDto;
import focandlol.mfa.datas.dto.MfaInitDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@ToString
@Entity
@Table(name="mfa")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
public class MfaEntity implements Serializable {

    @Id
    @Column
    private long id;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 512, nullable = false, name = "secret_key")
    private String secretKey;

    @Column(length = 100)
    private String type;

    public MfaEntity(MfaDto mfaDto) {
        this.id = mfaDto.getId();
        this.username = mfaDto.getUsername();
        this.secretKey = mfaDto.getSecretKey();
        this.type = mfaDto.getType();
    }

    public MfaEntity(MfaInitDto mfaInitDto) {
        this.username = mfaInitDto.getUsername();
        this.secretKey = mfaInitDto.getSecretKey();
        this.type = mfaInitDto.getType();
    }
}
