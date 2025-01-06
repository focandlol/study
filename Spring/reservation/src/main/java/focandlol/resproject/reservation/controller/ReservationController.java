package focandlol.resproject.reservation.controller;

import focandlol.resproject.auth.dto.CustomUserDetails;
import focandlol.resproject.reservation.dto.*;
import focandlol.resproject.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 예약 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 예약 생성
     *
     * @param user    : 현재 로그인한 사용자 정보
     * @param request : 예약에 필요한 정보
     * @return : 예약 정보
     */
    @PostMapping("/reserve")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ReserveDto.Response> reserve(@AuthenticationPrincipal CustomUserDetails user,
                                                       @RequestBody @Valid ReserveDto.Request request) {
        return ResponseEntity.ok().body(reservationService.reserve(user
                , request));
    }

    /**
     * 예약 수정
     *
     * @param id      : 수정할 예약 id
     * @param user    : 현재 로그인한 사용자 정보
     * @param request : 예약 수정에 필요한 정보
     * @return : 수정된 예약 정보
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ReservationUpdateDto.Response> update(@PathVariable Long id
            , @AuthenticationPrincipal CustomUserDetails user
            , @RequestBody @Valid ReservationUpdateDto.Request request) {
        return ResponseEntity.ok().body(reservationService.update(id, user, request));
    }

    /**
     * 고객이 자신이 예약한 리스트 조회
     *
     * @param user                  현재 로그인한 사용자 정보
     * @param reservationSearchCond 예약 검색 조건 (가게 이름, 날짜, 상태, 정렬 조건 등)
     * @param pageable              페이징 정보
     * @return 조건에 따라 필터링된 고객의 예약 리스트
     */
    @GetMapping("/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<ReservationDto>> getListForCustomer(@AuthenticationPrincipal CustomUserDetails user
            , ReservationSearchCond reservationSearchCond, Pageable pageable) {

        return ResponseEntity.ok().body(
                reservationService.getReservationForCustomer(user, reservationSearchCond, pageable));
    }

    /**
     * 매니저 자신이 관리하는 가게들의 예약 리스트
     *
     * @param user                  현재 로그인한 사용자 정보
     * @param reservationSearchCond 예약 검색 조건 (가게 이름, 날짜, 상태, 정렬 조건 등)
     * @param pageable              페이징 정보
     * @return 조건에 따라 필터링된 고객의 예약 리스트
     */
    @GetMapping("/manager")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<ReservationDto>> getListForManager(@AuthenticationPrincipal CustomUserDetails user
            , ReservationSearchCond reservationSearchCond, Pageable pageable) {

        return ResponseEntity.ok().body(
                reservationService.getReservationForManager(user, reservationSearchCond, pageable));
    }

    /**
     * 예약 상태 변경
     *
     * @param reservationId : 상태를 변경할 예약 id
     * @param user          : 현재 로그인한 사용자 정보
     * @param request       : 승인할지 취소할지 결정
     * @return : 변경된 예약 상태 -> APPROVED or CANCELED
     */
    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ReservationStatusDto.Response> changeStatus(@PathVariable(name = "id") Long reservationId
            , @AuthenticationPrincipal CustomUserDetails user
            , @RequestBody @Valid ReservationStatusDto.Request request) {
        return ResponseEntity.ok().body(reservationService.changeStatus(reservationId, user, request));
    }

    /**
     * 키오스크 도착
     *
     * @param request : 예약 확인을 위한 정보
     * @return : 변경된 예약 상태 -> VISITED
     */
    @PutMapping("/arrive")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ReservationArriveDto.Response> arrive(@RequestBody @Valid ReservationArriveDto.Request request) {
        return ResponseEntity.ok().body(reservationService.arrive(request));
    }

    /**
     * 예약 전 가게 세부 정보 확인 후 이거 호출해서 예약 가능 날짜 확인
     * 현재 날짜를 기준으로 해당 달의 모든 날짜의 남은 예약 가능 인원수 리스트 조회
     * ex) 현재 날짜 = 2025-01-02 -> 2025년 1월의 모든 날짜의 남은 예약 가능 인원수 조회
     *
     * @param storeId : 리스트를 조회할 가게 id
     * @return 현재 날짜를 기준으로 해당 달의 모든 날짜의 남은 예약 가능 인원수 리스트
     */
    @GetMapping("/leftSeat/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('MANAGER')")
    public ResponseEntity<List<ReservationNumMonth>> getLeftSeat(@PathVariable(name = "id") Long storeId) {
        return ResponseEntity.ok().body(reservationService.getReservationNumMonth(storeId));
    }
}
