package focandlol.mfa.datas.dto;

import focandlol.mfa.datas.entity.MfaEntity;
import lombok.*;

import java.io.Serializable;
import java.util.Optional;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
public class MfaDto implements Serializable {
    private long id;
    private String username;
    private String secretKey;
    private String type;
    private String otpNumber;

    public MfaDto(MfaEntity mfaEntity) {
        if(Optional.ofNullable(mfaEntity).isPresent()) {
            this.id = mfaEntity.getId();
            this.username = mfaEntity.getUsername();
            this.secretKey = mfaEntity.getSecretKey();
            this.type = mfaEntity.getType();
        }
    }
}
