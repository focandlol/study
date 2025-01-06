package focandlol.reservation.store.service;

import focandlol.reservation.auth.dto.CustomUserDetails;
import focandlol.reservation.store.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService {
    AddStoreDto.Response addStore(CustomUserDetails user, AddStoreDto.Request request);

    UpdateStoreDto.Response updateStore(Long storeId, CustomUserDetails user,
                                        UpdateStoreDto.Request request);

    void deleteStore(Long storeId, CustomUserDetails user);

    List<StoreDto> getAllStores(StoreSearchCond storeSearchCond, Pageable pageable);

    StoreDetailDto getStoreDetails(Long storeId);
}
