package focandlol.reservation.manager.repository;

import focandlol.reservation.manager.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {
    boolean existsByUsername(String username);
    Optional<ManagerEntity> findByUsername(String username);
}
