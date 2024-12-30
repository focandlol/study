package focandlol.reservation.service.store;

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

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final ManagerRepository managerRepository;
    private final StoreRepository storeRepository;
    private final QueryStoreRepository queryStoreRepository;

    public AddStoreDto.Response addStore(AddStoreDto.Request request){

        ManagerEntity manager = managerRepository.findById(request.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        if(storeRepository.existsByStoreNameAndLocation(request.getStoreName(), request.getLocation())){
            throw new RuntimeException("Store already exists");
        }

        return AddStoreDto.Response.from(storeRepository.save(request.toEntity(manager)));
    }


    public UpdateStoreDto.Response updateStore(Long storeId, UpdateStoreDto.Request request){
        ManagerEntity manager = managerRepository.findById(request.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        StoreEntity store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        if(!manager.getId().equals(store.getManager().getId())){
            throw new RuntimeException("another people");
        }

        store.setStoreName(request.getStoreName());
        store.setLocation(request.getLocation());
        store.setStorePhoneNumber(request.getStorePhoneNumber());
        store.setCesco(request.isCesco());
        store.setTotalSeat(request.getTotalSeat());

        return UpdateStoreDto.Response.from(store);
    }

    public void deleteStore(Long storeId, Long managerId){
        ManagerEntity manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        StoreEntity store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        if(!manager.getId().equals(store.getManager().getId())){
            throw new RuntimeException("another people");
        }

        storeRepository.delete(store);
    }


    public List<StoreDto> getAllStores(StoreSearchCond storeSearchCond, String storeName, Pageable pageable){
        Page<StoreEntity> stores = queryStoreRepository.findStores(storeSearchCond, storeName, pageable);
        return stores.stream().map(a -> StoreDto.from(a))
                .collect(Collectors.toList());
    }

    public StoreDetailDto getStoreDetails(Long storeId){
        StoreEntity store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        return StoreDetailDto.from(store);
    }
}