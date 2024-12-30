package focandlol.reservation.dto;

import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.entity.StoreEntity;
import focandlol.reservation.entity.auth.CustomerEntity;
import focandlol.reservation.type.ReservationType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

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
