package focandlol.resproject.store.repository;

import focandlol.resproject.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 가게 엔티티에 대한 repository 인터페이스
 */
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    /**
     * 가게 이름과 위치를 기준으로 가게의 존재 여부를 확인
     *
     * @param storeName 가게 이름
     * @param location  가게 위치
     * @return 해당 이름과 위치를 가진 가게가 존재하면 true, 그렇지 않으면 false
     */
    boolean existsByStoreNameAndLocation(String storeName, String location);
}
