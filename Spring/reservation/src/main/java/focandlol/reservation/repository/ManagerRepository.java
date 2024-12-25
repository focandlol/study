package focandlol.reservation.repository;

import focandlol.reservation.entity.CustomerEntity;
import focandlol.reservation.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {
    Optional<ManagerEntity> findByUsername(String username);
}
