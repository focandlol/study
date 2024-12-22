package focandlol.mfa.datas.dto;

import focandlol.mfa.datas.entity.MfaEntity;
import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class MfaProveDto implements Serializable {

    private String secretKey;
    private String type;

    public MfaProveDto(MfaEntity mfaEntity) {
        this.secretKey = mfaEntity.getSecretKey();
        this.type = mfaEntity.getType();
    }

}
