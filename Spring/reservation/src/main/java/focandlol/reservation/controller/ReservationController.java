package focandlol.reservation.controller;

import focandlol.reservation.dto.*;
import focandlol.reservation.repository.ReservationRepository;
import focandlol.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reserve")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> reserve(@RequestBody ReserveDto.Request request){
        return ResponseEntity.ok().body(reservationService.reserve(request));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> update(@PathVariable Long id
            , @RequestBody ReservationUpdateDto.Request request){
        return ResponseEntity.ok().body(reservationService.update(id,request));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getListForCustomer(@PathVariable(name = "id") Long customerId
            , ReservationSearchCond reservationSearchCond, Pageable pageable){

        return ResponseEntity.ok().body(
                reservationService.getReservationForCustomer(customerId,reservationSearchCond,pageable));
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<?> getListForManager(@PathVariable(name = "id") Long managerId
            , ReservationSearchCond reservationSearchCond, Pageable pageable){

        return ResponseEntity.ok().body(
                reservationService.getReservationForManager(managerId,reservationSearchCond,pageable));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable(name = "id") Long reservationId, @RequestBody ReservationStatusDto.Request request){
        return ResponseEntity.ok().body(reservationService.changeStatus(reservationId,request));
    }

    @PutMapping("/arrive")
    public ResponseEntity<?> arrive(@RequestBody ReservationArriveDto.Request request){
        return ResponseEntity.ok().body(reservationService.arrive(request));
    }


}
