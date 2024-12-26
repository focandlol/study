package focandlol.reservation.repository.store;

import focandlol.reservation.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    boolean existsByStoreNameAndLocation(String storeName, String location);
}
