package focandlol.reservation.controller;

import focandlol.reservation.dto.*;
import focandlol.reservation.repository.ReservationRepository;
import focandlol.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reserve")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> reserve(@AuthenticationPrincipal CustomUserDetails user,
                                         @RequestBody ReserveDto.Request request){
        return ResponseEntity.ok().body(reservationService.reserve(user.getId()
                ,request));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> update(@PathVariable Long id
            , @AuthenticationPrincipal CustomUserDetails user
            , @RequestBody ReservationUpdateDto.Request request){
        return ResponseEntity.ok().body(reservationService.update(id,user.getId(),request));
    }

    @GetMapping("/customer")
    public ResponseEntity<?> getListForCustomer(@AuthenticationPrincipal CustomUserDetails user
            , ReservationSearchCond reservationSearchCond, Pageable pageable){

        return ResponseEntity.ok().body(
                reservationService.getReservationForCustomer(user.getId(),reservationSearchCond,pageable));
    }

    @GetMapping("/manager")
    public ResponseEntity<?> getListForManager(@AuthenticationPrincipal CustomUserDetails user
            , ReservationSearchCond reservationSearchCond, Pageable pageable){

        return ResponseEntity.ok().body(
                reservationService.getReservationForManager(user.getId(),reservationSearchCond,pageable));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable(name = "id") Long reservationId
            , @AuthenticationPrincipal CustomUserDetails user
            , @RequestBody ReservationStatusDto.Request request){
        return ResponseEntity.ok().body(reservationService.changeStatus(reservationId,user.getId(),request));
    }

    @PutMapping("/arrive")
    public ResponseEntity<?> arrive(@RequestBody ReservationArriveDto.Request request){
        return ResponseEntity.ok().body(reservationService.arrive(request));
    }


}
