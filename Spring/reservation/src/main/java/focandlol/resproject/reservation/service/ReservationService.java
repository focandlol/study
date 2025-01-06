package focandlol.resproject.reservation.service;

import focandlol.resproject.auth.dto.CustomUserDetails;
import focandlol.resproject.reservation.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 매니저 서비스 인터페이스
 */
public interface ReservationService {

    /**
     * 예약 추가
     */
    ReserveDto.Response reserve(CustomUserDetails user, ReserveDto.Request request);

    /**
     * 예약 수정
     */
    ReservationUpdateDto.Response update(Long reservationId, CustomUserDetails user, ReservationUpdateDto.Request request);

    /**
     * 예약 상태 변경
     */
    ReservationStatusDto.Response changeStatus(Long reservationId, CustomUserDetails user,
                                               ReservationStatusDto.Request request);

    /**
     * 키오스크 도착
     */
    ReservationArriveDto.Response arrive(ReservationArriveDto.Request request);

    /**
     * 고객 본인의 예약 목록
     */
    List<ReservationDto> getReservationForCustomer(CustomUserDetails user, ReservationSearchCond reservationSearchCond
            , Pageable pageable);

    /**
     * 매니저가 관리하는 가게들의 예약 목록
     */
    List<ReservationDto> getReservationForManager(CustomUserDetails user, ReservationSearchCond reservationSearchCond,
                                                  Pageable pageable);

    /**
     * 현재 날짜를 기준으로 해당 달의 모든 날짜의 남은 예약 가능 인원수 목록
     */
    List<ReservationNumMonth> getReservationNumMonth(Long storeId);
}
