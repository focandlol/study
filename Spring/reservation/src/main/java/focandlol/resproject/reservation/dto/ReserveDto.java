package focandlol.resproject.reservation.dto;

import focandlol.resproject.customer.entity.CustomerEntity;
import focandlol.resproject.reservation.entity.ReservationEntity;
import focandlol.resproject.reservation.type.ReservationType;
import focandlol.resproject.store.entity.StoreEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 예약 추가
 */
public class ReserveDto {

    /**
     * 예약 추가 요청
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        /**
         * 가게 id
         */
        @NotNull
        private Long storeId;

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
         * 예약 인원수
         */
        @NotNull
        private Integer numOfPeople;

        /**
         * Request 데이터 기반으로 ReservationEntity 객체를 생성
         *
         * @param storeEntity    가게 엔티티
         * @param customerEntity 고객 엔티티
         * @return ReservationEntity 객체
         */
        public ReservationEntity toEntity(StoreEntity storeEntity, CustomerEntity customerEntity) {
            return ReservationEntity.builder()
                    .store(storeEntity)
                    .customer(customerEntity)
                    .date(date)
                    .time(time)
                    .numOfPeople(numOfPeople)
                    .reservationType(ReservationType.UNAPPROVED)
                    .build();
        }
    }

    /**
     * 예약 추가 응답
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
         * @param reservationEntity 예약 엔티티 객체
         * @return 변환된 Response 객체
         */
        public static Response from(ReservationEntity reservationEntity) {
            return Response.builder()
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
