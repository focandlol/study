package focandlol.reservation.controller;

import focandlol.reservation.dto.ReserveDto;
import focandlol.reservation.repository.ReservationRepository;
import focandlol.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
