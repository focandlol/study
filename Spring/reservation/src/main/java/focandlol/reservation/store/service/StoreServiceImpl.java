package focandlol.reservation.store.service;

import focandlol.reservation.auth.dto.CustomUserDetails;
import focandlol.reservation.global.exception.CustomException;
import focandlol.reservation.store.entity.StoreEntity;
import focandlol.reservation.manager.entity.ManagerEntity;
import focandlol.reservation.manager.repository.ManagerRepository;
import focandlol.reservation.store.repository.QueryStoreRepository;
import focandlol.reservation.store.repository.StoreRepository;
import focandlol.reservation.store.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static focandlol.reservation.global.exception.ErrorCode.*;

/**
 * 가게 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {

    private final ManagerRepository managerRepository;
    private final StoreRepository storeRepository;
    private final QueryStoreRepository queryStoreRepository;

    /**
     * 가게 생성
     * @param user : 현재 로그인한 사용자 정보
     * @param request : 가게 생성에 필요한 정보
     * @return : 생성된 가게 정보
     */
    @Override
    public AddStoreDto.Response addStore(CustomUserDetails user, AddStoreDto.Request request){

        /**
         * 가게 생성하려는 매니저 조회
         */
        ManagerEntity manager = getManagerOrElseThrow(user.getId());

        /**
         * 가게 이름, 위치가 같은 가게가 있는지 확인
         */
        if(storeRepository.existsByStoreNameAndLocation(request.getStoreName(), request.getLocation())){
            throw new CustomException(ALREADY_EXISTS_STORE);
        }

        return AddStoreDto.Response.from(storeRepository.save(request.toEntity(manager)));
    }

    /**
     * 가게 수정
     * @param storeId : 수정하려는 가게 id
     * @param user : 현재 로그인한 사용자 정보
     * @param request : 가게 수정에 필요한 정보
     * @return : 수정된 가게 정보
     */
    @Override
    public UpdateStoreDto.Response updateStore(Long storeId, CustomUserDetails user,
                                               UpdateStoreDto.Request request){

        /**
         * 가게가 존재하는지 확인
         */
        StoreEntity store = getStoreOrElseThrow(storeId);

        /**
         * 가게 주인과 현재 로그인한 사용자가 동일 인물인지 확인
         */
        isSamePeople(user,store);

        store.setStoreName(request.getStoreName());
        store.setLocation(request.getLocation());
        store.setDescription(request.getDescription());
        store.setStorePhoneNumber(request.getStorePhoneNumber());
        store.setCesco(request.isCesco());
        store.setTotalSeat(request.getTotalSeat());

        return UpdateStoreDto.Response.from(store);
    }

    /**
     * 가게 삭제
     * @param storeId : 삭제하려는 가게 id
     * @param user : 현재 로그인한 사용자 id
     */
    @Override
    public void deleteStore(Long storeId, CustomUserDetails user){

        /**
         * 가게가 존재하는지 확인
         */
        StoreEntity store = getStoreOrElseThrow(storeId);

        /**
         * 가게 주인과 현재 로그인한 사용자가 동일 인물인지 확인
         */
        isSamePeople(user,store);

        storeRepository.delete(store);
    }

    /**
     * 가게 목록
     * @param storeSearchCond : 가게 조회 조건
     * storeSearchCond.storeName : 가게 이름으로 검색
     * storeSearchCond.sortBy : 정렬 조건
     * STORE_NAME : 가게 이름으로 정렬
     * STAR : 가게 별점으로 정렬
     * storeSearchCond.ascending : 내림차순 or 오름차순
     * 동적 퀴리 생성을 위해 querydsl 사용하는 queryStoreRepository 사용
     * @param pageable : 페이징
     * @return : 조건에 맞게 조회된 가게들
     */
    @Override
    public List<StoreDto> getAllStores(StoreSearchCond storeSearchCond, Pageable pageable){
        Page<StoreEntity> stores = queryStoreRepository.findStores(storeSearchCond, pageable);
        return stores.stream().map(a -> StoreDto.from(a))
                .collect(Collectors.toList());
    }

    /**
     * 가게 디테일 정보
     * @param storeId : 세부 정보를 볼 가게 id
     * @return: 가게 세부 정보
     */
    @Override
    public StoreDetailDto getStoreDetails(Long storeId){
        /**
         * 가게가 존재하는지 확인
         */
        StoreEntity store = getStoreOrElseThrow(storeId);

        return StoreDetailDto.from(store);
    }

    /**
     * 사용자를 조회하고 만약 사용자가 없다면 예외 발생
     */
    private ManagerEntity getManagerOrElseThrow(Long managerId) {
        return managerRepository.findById(managerId)
                .orElseThrow(() -> new CustomException(MANAGER_NOT_FOUND));
    }

    /**
     * 가게를 조회하고 만약 가게가 없다면 예외 발생
     */
    private StoreEntity getStoreOrElseThrow(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));
    }

    /**
     * 가게 주인과 로그인한 사용자가 동일 인물인지 확인하고 다르면 예외 발생
     */
    private void isSamePeople(CustomUserDetails user, StoreEntity store){
        if(!user.getId().equals(store.getManager().getId())){
            throw new CustomException(ANOTHER_MANAGER);
        }
    }
}
