package focandlol.resproject.reservation.dto;

import focandlol.resproject.reservation.entity.ReservationEntity;
import focandlol.resproject.reservation.type.ReservationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 키오스크 도착 dto
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
    public static class Request {
        /**
         * 고객 이메일.
         */
        @NotNull
        @Email
        private String username;

        /**
         * 가게 id.
         */
        @NotNull
        private Long storeId;

        /**
         * 예약 날짜.
         */
        @NotNull
        private LocalDate date;

        /**
         * 예약 시간.
         */
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
    public static class Response {
        /**
         * 예약 id.
         */
        private Long reservationId;

        /**
         * 예약 상태.
         */
        private ReservationType reservationType;

        /**
         * ReservationEntity 객체를 Response dto로 변환
         *
         * @param reservationEntity 예약 엔티티 객체
         * @return 변환된 응답 dto
         */
        public static ReservationArriveDto.Response from(ReservationEntity reservationEntity) {
            return ReservationArriveDto.Response.builder()
                    .reservationId(reservationEntity.getId())
                    .reservationType(reservationEntity.getReservationType())
                    .build();
        }
    }
}
