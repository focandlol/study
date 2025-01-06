package focandlol.reservation.reservation.dto;

import focandlol.reservation.reservation.entity.ReservationEntity;
import focandlol.reservation.reservation.type.ReservationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 키오스크 도착
 */
public class ReservationArriveDto {

    /**
     * 키오스크 도착 요청
     */
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

    /**
     * 키오스크 도착 응답
     */
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
