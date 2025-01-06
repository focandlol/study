package focandlol.reservation.reservation.dto;

import focandlol.reservation.reservation.entity.ReservationEntity;
import focandlol.reservation.reservation.type.ReservationType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 예약 수정
 */
public class ReservationUpdateDto {

    /**
     * 예약 수정 요청
     */
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

    /**
     * 예약 수정 응답
     */
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
