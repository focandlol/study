package focandlol.reservation.service.store;

import focandlol.reservation.exception.CustomException;
import focandlol.reservation.dto.store.*;
import focandlol.reservation.entity.StoreEntity;
import focandlol.reservation.entity.auth.ManagerEntity;
import focandlol.reservation.repository.ManagerRepository;
import focandlol.reservation.repository.store.QueryStoreRepository;
import focandlol.reservation.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static focandlol.reservation.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final ManagerRepository managerRepository;
    private final StoreRepository storeRepository;
    private final QueryStoreRepository queryStoreRepository;

    public AddStoreDto.Response addStore(Long managerId, AddStoreDto.Request request){

        ManagerEntity manager = getManager(managerId);

        if(storeRepository.existsByStoreNameAndLocation(request.getStoreName(), request.getLocation())){
            throw new CustomException(ALREADY_EXISTS_STORE);
        }

        return AddStoreDto.Response.from(storeRepository.save(request.toEntity(manager)));
    }


    public UpdateStoreDto.Response updateStore(Long storeId, Long managerId,
                                               UpdateStoreDto.Request request){
        StoreEntity store = getStore(storeId);

        isSamePeople(managerId,store);

        store.setStoreName(request.getStoreName());
        store.setLocation(request.getLocation());
        store.setStorePhoneNumber(request.getStorePhoneNumber());
        store.setCesco(request.isCesco());
        store.setTotalSeat(request.getTotalSeat());

        return UpdateStoreDto.Response.from(store);
    }

    public void deleteStore(Long storeId, Long managerId){
        StoreEntity store = getStore(storeId);

        isSamePeople(managerId,store);

        storeRepository.delete(store);
    }


    public List<StoreDto> getAllStores(StoreSearchCond storeSearchCond, String storeName, Pageable pageable){
        Page<StoreEntity> stores = queryStoreRepository.findStores(storeSearchCond, storeName, pageable);
        return stores.stream().map(a -> StoreDto.from(a))
                .collect(Collectors.toList());
    }

    public StoreDetailDto getStoreDetails(Long storeId){
        StoreEntity store = getStore(storeId);

        return StoreDetailDto.from(store);
    }

    private ManagerEntity getManager(Long managerId) {
        return managerRepository.findById(managerId)
                .orElseThrow(() -> new CustomException(MANAGER_NOT_FOUND));
    }

    private StoreEntity getStore(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));
    }

    private void isSamePeople(Long managerId, StoreEntity store){
        if(!managerId.equals(store.getManager().getId())){
            throw new CustomException(ANOTHER_MANAGER);
        }
    }
}
