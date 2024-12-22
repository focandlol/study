package focandlol.mfa.service;

import focandlol.mfa.datas.dto.MfaDto;
import focandlol.mfa.datas.dto.MfaInitDto;
import focandlol.mfa.datas.dto.MfaProveDto;
import focandlol.mfa.datas.entity.MfaEntity;
import focandlol.mfa.repository.MfaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MfaServiceImpl implements MfaService {

    private final MfaRepository mfaRepository;

    @Override
    public MfaDto getMfa(String username){
        return new MfaDto(mfaRepository.findByUsername(username).get());
    }

    @Override
    public MfaProveDto getMfaSecretKey(String username){
        return new MfaProveDto(mfaRepository.findByUsername(username).get());
    }

    @Override
    public MfaInitDto setMfa(MfaInitDto mfaInitDto){
        mfaRepository.save(new MfaEntity(mfaInitDto));
        return mfaInitDto;
    }

    @Override
    public MfaDto setMfa(MfaDto mfaDto){
        mfaRepository.save(new MfaEntity(mfaDto));
        return mfaDto;
    }

    @Override
    public void deleteMfa(String username){
        mfaRepository.delete(mfaRepository.findByUsername(username).get());
    }

}
