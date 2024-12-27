package focandlol.reservation.controller;

import focandlol.reservation.dto.store.AddStoreDto;
import focandlol.reservation.dto.store.StoreSearchCond;
import focandlol.reservation.service.store.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> addStore(@RequestBody @Valid AddStoreDto.Request request){
        return ResponseEntity.ok().body(storeService.addStore(request));
    }

    @GetMapping
    public ResponseEntity<?> getAllStore(@RequestParam String storeName
            , StoreSearchCond storeSearchCond
            , Pageable pageable){
        System.out.println("sortby" + storeSearchCond.getSortBy());
        System.out.println("asc" + storeSearchCond.isAscending());

        return ResponseEntity.ok().body(storeService.getAllStores(storeSearchCond, storeName, pageable));
    }
}
