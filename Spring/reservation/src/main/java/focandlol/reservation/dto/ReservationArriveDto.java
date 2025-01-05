package focandlol.reservation.dto;

import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.type.ReservationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationArriveDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        @NotNull
        @Email
        private String username;

        @NotNull
        private Long storeId;

        @NotNull
        private LocalDate date;

        @NotNull
        private LocalTime time;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long reservationId;

        private ReservationType reservationType;

        public static ReservationArriveDto.Response from(ReservationEntity reservationEntity){
            return ReservationArriveDto.Response.builder()
                    .reservationId(reservationEntity.getId())
                    .reservationType(reservationEntity.getReservationType())
                    .build();
        }
    }
}
