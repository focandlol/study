package focandlol.resproject.store.service;

import focandlol.resproject.auth.dto.CustomUserDetails;
import focandlol.resproject.store.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 가게 서비스 인터페이스
 */
public interface StoreService {
    /**
     * 가게 추가
     */
    AddStoreDto.Response addStore(CustomUserDetails user, AddStoreDto.Request request);

    /**
     * 가게 수정
     */
    UpdateStoreDto.Response updateStore(Long storeId, CustomUserDetails user,
                                        UpdateStoreDto.Request request);

    /**
     * 가게 삭제
     */
    void deleteStore(Long storeId, CustomUserDetails user);

    /**
     * 가게 목록
     */
    List<StoreDto> getAllStores(StoreSearchCond storeSearchCond, Pageable pageable);

    /**
     * 가게 세부 사항
     */
    StoreDetailDto getStoreDetails(Long storeId);
}
