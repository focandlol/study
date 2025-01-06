package focandlol.reservation.reservation.dto;

import focandlol.reservation.reservation.entity.ReservationEntity;
import focandlol.reservation.reservation.type.ReservationType;
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
    private Long reservationId;

    private Long customerId;

    private Long storeId;

    private LocalDate date;

    private LocalTime time;

    private ReservationType reservationType;

    private Integer NumOfPeople;

    public static ReservationDto from(ReservationEntity reservationEntity) {
        return ReservationDto.builder()
                .reservationId(reservationEntity.getId())
                .customerId(reservationEntity.getCustomer().getId())
                .storeId(reservationEntity.getStore().getId())
                .date(reservationEntity.getDate())
                .time(reservationEntity.getTime())
                .reservationType(reservationEntity.getReservationType())
                .NumOfPeople(reservationEntity.getNumOfPeople())
                .build();
    }
}
