package focandlol.reservation.customer.repository;

import focandlol.reservation.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByUsername(String username);

    Optional<CustomerEntity> findByUsername(String username);
}
