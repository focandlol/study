package focandlol.resproject.reservation.dto;

import focandlol.resproject.reservation.entity.ReservationEntity;
import focandlol.resproject.reservation.type.ReservationType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 예약 정보 dto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {
    /**
     * 예약 id.
     */
    private Long reservationId;

    /**
     * 고객 id
     */
    private Long customerId;

    /**
     * 가게 id
     */
    private Long storeId;

    /**
     * 예약 날짜
     */
    private LocalDate date;

    /**
     * 예약 시간
     */
    private LocalTime time;

    /**
     * 예약 상태
     */
    private ReservationType reservationType;

    /**
     * 예약 인원 수
     */
    private Integer numOfPeople;

    /**
     * ReservationEntity 객체를 ReservationDto 객체로 변환
     *
     * @param reservationEntity 변환할 ReservationEntity 객체
     * @return ReservationDto 객체
     */
    public static ReservationDto from(ReservationEntity reservationEntity) {
        return ReservationDto.builder()
                .reservationId(reservationEntity.getId())
                .customerId(reservationEntity.getCustomer().getId())
                .storeId(reservationEntity.getStore().getId())
                .date(reservationEntity.getDate())
                .time(reservationEntity.getTime())
                .reservationType(reservationEntity.getReservationType())
                .numOfPeople(reservationEntity.getNumOfPeople())
                .build();
    }
}
