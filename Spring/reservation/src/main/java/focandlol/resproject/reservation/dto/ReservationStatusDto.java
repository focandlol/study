package focandlol.resproject.reservation.dto;

import focandlol.resproject.reservation.entity.ReservationEntity;
import focandlol.resproject.reservation.type.ReservationType;
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
    public static class Request {
        /**
         * 예약 상태
         */
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
    public static class Response {
        /**
         * 예약 id
         */
        private Long reservationId;

        /**
         * 예약 상태
         */
        private ReservationType reservationType;

        /**
         * ReservationEntity 객체를 Response 객체로 변환
         *
         * @param reservationEntity 변환할 ReservationEntity 객체
         * @return ReservationDto 객체
         */
        public static Response from(ReservationEntity reservationEntity) {
            return Response.builder()
                    .reservationId(reservationEntity.getId())
                    .reservationType(reservationEntity.getReservationType())
                    .build();
        }
    }
}
