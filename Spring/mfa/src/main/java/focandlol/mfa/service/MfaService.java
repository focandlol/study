package focandlol.mfa.service;

import focandlol.mfa.datas.dto.MfaDto;
import focandlol.mfa.datas.dto.MfaInitDto;
import focandlol.mfa.datas.dto.MfaProveDto;

public interface MfaService {
    MfaDto getMfa(String username);

    MfaProveDto getMfaSecretKey(String username);

    MfaInitDto setMfa(MfaInitDto mfaInitDto);

    MfaDto setMfa(MfaDto mfaDto);

    void deleteMfa(String username);
}
