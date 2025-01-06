package focandlol.reservation.reservation.controller;

import focandlol.reservation.auth.dto.CustomUserDetails;
import focandlol.reservation.reservation.dto.*;
import focandlol.reservation.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 예약 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 예약
     * @param user : 현재 로그인한 사용자 정보
     * @param request : 예약에 필요한 정보
     * @return : 예약 정보
     */
    @PostMapping("/reserve")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> reserve(@AuthenticationPrincipal CustomUserDetails user,
                                         @RequestBody @Valid ReserveDto.Request request){
        return ResponseEntity.ok().body(reservationService.reserve(user
                ,request));
    }

    /**
     * 예약 수정
     * @param id : 수정할 예약 id
     * @param user : 현재 로그인한 사용자 정보
     * @param request : 예약 수정에 필요한 정보
     * @return : 수정된 예약 정보
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> update(@PathVariable Long id
            , @AuthenticationPrincipal CustomUserDetails user
            , @RequestBody @Valid ReservationUpdateDto.Request request){
        return ResponseEntity.ok().body(reservationService.update(id,user,request));
    }

    /**
     * customer 자신이 예약한 리스트
     * @param user : 현재 로그인한 사용자 정보
     * @param reservationSearchCond : 예약 검색 조건
     * reservationSearchCond.storeName : 가게 이름으로 검색
     * reservationSearchCond.date : 날짜로 검색
     * reservationSearchCond.sortBy : 정렬 조건
     * STORE_NAME : 가게 이름으로 정렬
     * DATE : 날짜로 정렬
     * TIME : 시간으로 정렬
     * reservationSearchCond.reservationType : 예약 상태로 검색
     * UNAPPROVED : 예약을 했지만 아직 가게 주인 승인이 되지 않은 상태
     * APPROVED : 가게 주인이 승인한 상태
     * VISITED : 방문 완료한 상태
     * CANCELED : 예약이 취소된 상태
     * reservation.isAscending : 오름차순 내림차순
     * @param pageable : 페이징
     * @return : 조건에 따라 검색된 해당 고객이 예약한 리스트
     */
    @GetMapping("/customer")
    public ResponseEntity<?> getListForCustomer(@AuthenticationPrincipal CustomUserDetails user
            , ReservationSearchCond reservationSearchCond, Pageable pageable){

        return ResponseEntity.ok().body(
                reservationService.getReservationForCustomer(user,reservationSearchCond,pageable));
    }

    /**
     * manager 자신이 관리하는 가게들의 예약 리스트
     * @param user : 현재 로그인한 사용자 정보
     * @param reservationSearchCond : 예약 검색 조건
     * reservationSearchCond.storeName : 가게 이름으로 검색
     * reservationSearchCond.date : 날짜로 검색
     * reservationSearchCond.sortBy : 정렬 조건
     * STORE_NAME : 가게 이름으로 정렬
     * DATE : 날짜로 정렬
     * TIME : 시간으로 정렬
     * reservationSearchCond.reservationType : 예약 상태로 검색
     * UNAPPROVED : 예약을 했지만 아직 가게 주인 승인이 되지 않은 상태
     * APPROVED : 가게 주인이 승인한 상태
     * VISITED : 방문 완료한 상태
     * CANCELED : 예약이 취소된 상태
     * reservation.isAscending : 오름차순 내림차순
     * @param pageable : 페이징
     * @return : 조건에 따라 검색된 해당 manager가 관리하는 가게의 예약 리스트
     */
    @GetMapping("/manager")
    public ResponseEntity<?> getListForManager(@AuthenticationPrincipal CustomUserDetails user
            , ReservationSearchCond reservationSearchCond, Pageable pageable){

        return ResponseEntity.ok().body(
                reservationService.getReservationForManager(user,reservationSearchCond,pageable));
    }

    /**
     * 예약 승인 및 취소
     * @param reservationId : 상태를 변경할 예약 id
     * @param user : 현재 로그인한 사용자 정보
     * @param request : 승인할지 취소할지 결정
     * @return : 변경된 예약 상태 -> APPROVED or CANCELED
     */
    @PutMapping("/status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable(name = "id") Long reservationId
            , @AuthenticationPrincipal CustomUserDetails user
            , @RequestBody @Valid ReservationStatusDto.Request request){
        return ResponseEntity.ok().body(reservationService.changeStatus(reservationId,user,request));
    }

    /**
     * 키오스크 도착
     * @param request : 예약 확인을 위한 정보
     * @return : 변경된 예약 상태 -> VISITED
     */
    @PutMapping("/arrive")
    public ResponseEntity<?> arrive(@RequestBody @Valid ReservationArriveDto.Request request){
        return ResponseEntity.ok().body(reservationService.arrive(request));
    }

    /**
     * 예약 전 가게 세부 정보 확인 후 이거 호출해서 예약 가능 날짜 확인
     * 현재 날짜를 기준으로 해당 달의 모든 날짜의 남은 예약 가능 인원수 리스트 조회
     * ex) 현재 날짜 = 2025-01-02 -> 2025년 1월의 모든 날짜의 남은 예약 가능 인원수 조회
     * @param storeId : 리스트를 조회할 가게 id
     * @return 현재 날짜를 기준으로 해당 달의 모든 날짜의 남은 예약 가능 인원수 리스트
     */
    @GetMapping("/leftSeat/{id}")
    public ResponseEntity<?> getLeftSeat(@PathVariable(name = "id") Long storeId){
        return ResponseEntity.ok().body(reservationService.getReservationNumMonth(storeId));
    }


}
