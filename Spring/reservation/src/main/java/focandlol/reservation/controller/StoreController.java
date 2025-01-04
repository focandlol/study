package focandlol.reservation.controller;

import focandlol.reservation.dto.CustomUserDetails;
import focandlol.reservation.dto.store.AddStoreDto;
import focandlol.reservation.dto.store.StoreSearchCond;
import focandlol.reservation.dto.store.UpdateStoreDto;
import focandlol.reservation.service.store.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

/**
 * 가게 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    /**
     * 가게 추가
     * @param user : 현재 로그인한 사용자 정보
     * @param request : 가게 생성에 필요한 정보
     * @return : 생성된 매장 정보
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> addStore(@AuthenticationPrincipal CustomUserDetails user,
                                          @RequestBody @Valid AddStoreDto.Request request){
        return ResponseEntity.status(CREATED).body(storeService.addStore(user,request));
    }

    /**
     * 가게 업데이트
     * @param user : 현재 로그인한 사용자 정보
     * @param request : 가게 수정에 필요한 정보
     * @param id : 수정할 가게의 id
     * @return : 수정된 매장 정보
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> updateStore(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody @Valid UpdateStoreDto.Request request,
                                         @PathVariable Long id){
        return ResponseEntity.ok().body(storeService.updateStore(id, user, request));
    }

    /**
     * 가게 삭제
     * @param id : 삭제할 가게 id
     * @param user : 현재 로그인한 사용자 정보
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public void deleteStore(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails user){
        storeService.deleteStore(id, user);
    }

    /**
     * 가게 목록
     * @param storeSearchCond : 가게 조회 조건
     * storeSearchCond.storeName : 가게 이름으로 검색
     * storeSearchCond.sortBy : 정렬 조건
     * STORE_NAME : 가게 이름으로 정렬
     * STAR : 가게 별점으로 정렬
     * storeSearchCond.ascending : 내림차순 or 오름차순
     * @param pageable : 페이징
     * @return : 조건에 맞게 조회된 가게들
     */
    @GetMapping
    public ResponseEntity<?> getAllStore(StoreSearchCond storeSearchCond
            , Pageable pageable){
        return ResponseEntity.ok().body(storeService.getAllStores(storeSearchCond, pageable));
    }

    /**
     * 가게 디테일 정보
     * @param id : 세부 정보를 볼 가게 id
     * @return : 가게 세부 정보
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getStoreDetails(@PathVariable Long id){
        return ResponseEntity.ok().body(storeService.getStoreDetails(id));
    }
}
