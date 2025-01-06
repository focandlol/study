package focandlol.resproject.manager.repository;

import focandlol.resproject.manager.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 매니저 엔티티(ManagerEntity)에 대한 repository 인터페이스
 */
public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {

    /**
     * username을 기준으로 매니저 존재 여부 확인
     *
     * @param username 확인할 이메일
     * @return true: 해당 이메일을 가진 매니저가 존재함, false: 존재하지 않음
     */
    boolean existsByUsername(String username);

    /**
     * username 기준으로 매니저 정보를 조회
     *
     * @param username 조회할 이메일
     * @return 조회된 매니저 정보가 포함된 Optional 객체
     */
    Optional<ManagerEntity> findByUsername(String username);
}
