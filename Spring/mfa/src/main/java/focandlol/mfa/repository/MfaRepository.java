package focandlol.mfa.repository;

import focandlol.mfa.datas.entity.MfaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MfaRepository extends JpaRepository<MfaEntity, Long> {
    Optional<MfaEntity> findByUsername(String username);
}
