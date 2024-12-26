package focandlol.reservation.service.store;

import focandlol.reservation.dto.store.AddStoreDto;
import focandlol.reservation.entity.auth.ManagerEntity;
import focandlol.reservation.repository.ManagerRepository;
import focandlol.reservation.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final ManagerRepository managerRepository;
    private final StoreRepository storeRepository;

    public AddStoreDto.Response addStore(AddStoreDto.Request request){

        ManagerEntity manager = managerRepository.findById(request.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        if(storeRepository.existsByStoreNameAndLocation(request.getStoreName(), request.getLocation())){
            throw new RuntimeException("Store already exists");
        }

        return AddStoreDto.Response.from(storeRepository.save(request.toEntity(manager)));
    }
}
