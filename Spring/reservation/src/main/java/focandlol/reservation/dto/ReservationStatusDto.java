package focandlol.reservation.dto;

import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.type.ReservationType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class ReservationStatusDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        @NotNull
        private ReservationType reservationType;
    }

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
