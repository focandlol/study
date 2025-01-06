package focandlol.reservation.store.repository;

import focandlol.reservation.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    boolean existsByStoreNameAndLocation(String storeName, String location);
}
