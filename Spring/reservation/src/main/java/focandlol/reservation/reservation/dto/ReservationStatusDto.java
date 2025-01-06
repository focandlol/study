package focandlol.reservation.reservation.dto;

import focandlol.reservation.reservation.entity.ReservationEntity;
import focandlol.reservation.reservation.type.ReservationType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 예약 상태 변경
 */
public class ReservationStatusDto {

    /**
     * 예약 상태 변경 요청
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        @NotNull
        private ReservationType reservationType;
    }

    /**
     * 예약 상태 변경 응답
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long reservationId;

        private ReservationType reservationType;

        public static Response from(ReservationEntity reservationEntity){
            return Response.builder()
                    .reservationId(reservationEntity.getId())
                    .reservationType(reservationEntity.getReservationType())
                    .build();
        }
    }
}
