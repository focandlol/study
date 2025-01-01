package focandlol.reservation.dto;

import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.type.ReservationType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationUpdateDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        @NotNull
        private LocalDate date;

        @NotNull
        private LocalTime time;

        @NotNull
        private Integer NumOfPeople;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private Long storeId;

        private Long customerId;

        private LocalDate date;

        private LocalTime time;

        private Integer NumOfPeople;

        private ReservationType reservationType;

        public static ReservationUpdateDto.Response from(ReservationEntity reservationEntity){
            return ReservationUpdateDto.Response.builder()
                    .storeId(reservationEntity.getStore().getId())
                    .customerId(reservationEntity.getCustomer().getId())
                    .date(reservationEntity.getDate())
                    .time(reservationEntity.getTime())
                    .NumOfPeople(reservationEntity.getNumOfPeople())
                    .reservationType(reservationEntity.getReservationType())
                    .build();
        }
    }
}
