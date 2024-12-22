package focandlol.mfa.datas.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class MfaInitDto implements Serializable {
    private String username;
    private String secretKey;
    private String type;
}
