package focandlol.reservation.reservation.service;

import focandlol.reservation.auth.dto.CustomUserDetails;
import focandlol.reservation.reservation.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationService {
    ReserveDto.Response reserve(CustomUserDetails user, ReserveDto.Request request);

    ReservationUpdateDto.Response update(Long reservationId, CustomUserDetails user, ReservationUpdateDto.Request request);

    ReservationStatusDto.Response changeStatus(Long reservationId, CustomUserDetails user,
                                               ReservationStatusDto.Request request);

    ReservationArriveDto.Response arrive(ReservationArriveDto.Request request);

    List<ReservationDto> getReservationForCustomer(CustomUserDetails user, ReservationSearchCond reservationSearchCond
            , Pageable pageable);

    List<ReservationDto> getReservationForManager(CustomUserDetails user, ReservationSearchCond reservationSearchCond,
                                                  Pageable pageable);

    List<ReservationNumMonth> getReservationNumMonth(Long storeId);
}
