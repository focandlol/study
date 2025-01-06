package focandlol.resproject.reservation.dto;

import focandlol.resproject.reservation.entity.ReservationEntity;
import focandlol.resproject.reservation.type.ReservationType;
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

        /**
         * 예약 날짜
         */
        @NotNull
        private LocalDate date;

        /**
         * 예약 시간
         */
        @NotNull
        private LocalTime time;

        /**
         * 예약 인원 수
         */
        @NotNull
        private Integer numOfPeople;
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

        /**
         * 가게 id
         */
        private Long storeId;

        /**
         * 고객 id
         */
        private Long customerId;

        /**
         * 예약 날짜
         */
        private LocalDate date;

        /**
         * 예약 시간
         */
        private LocalTime time;

        /**
         * 예약 인원 수
         */
        private Integer numOfPeople;

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
        public static ReservationUpdateDto.Response from(ReservationEntity reservationEntity) {
            return ReservationUpdateDto.Response.builder()
                    .storeId(reservationEntity.getStore().getId())
                    .customerId(reservationEntity.getCustomer().getId())
                    .date(reservationEntity.getDate())
                    .time(reservationEntity.getTime())
                    .numOfPeople(reservationEntity.getNumOfPeople())
                    .reservationType(reservationEntity.getReservationType())
                    .build();
        }
    }
}
