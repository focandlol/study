package focandlol.resproject.customer.repository;

import focandlol.resproject.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 고객 엔티티(CustomerEntity)에 대한 repository 인터페이스
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    /**
     * username을 기준으로 고객 존재 여부 확인
     *
     * @param username 확인할 이메일
     * @return true: 해당 이메일을 가진 고객이 존재함, false: 존재하지 않음
     */
    boolean existsByUsername(String username);

    /**
     * username 기준으로 고객 정보를 조회
     *
     * @param username 조회할 이메일
     * @return 조회된 고객 정보가 포함된 Optional 객체
     */
    Optional<CustomerEntity> findByUsername(String username);
}
