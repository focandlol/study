package focandlol.resproject.review.dto;

import focandlol.resproject.customer.entity.CustomerEntity;
import focandlol.resproject.reservation.entity.ReservationEntity;
import focandlol.resproject.review.entity.ReviewEntity;
import focandlol.resproject.store.entity.StoreEntity;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 리뷰 작성
 */
public class AddReviewDto {

    /**
     * 리뷰 작성 요청
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        /**
         * 가게 id
         */
        @NotNull
        private Long storeId;

        /**
         * 예약 id
         */
        @NotNull
        private Long reservationId;

        /**
         * 리뷰 내용
         */
        @Size(max = 300)
        private String content;

        /**
         * 별점
         */
        @NotNull
        @Digits(integer = 1, fraction = 1)
        @DecimalMin("0.0")
        @DecimalMax("5.0")
        private BigDecimal star;

        /**
         * 엔티티 변환 메서드
         *
         * @param customer    리뷰 작성 고객
         * @param store       리뷰 대상 가게
         * @param reservation 리뷰 대상 예약
         * @return ReviewEntity 객체
         */
        public ReviewEntity toEntity(CustomerEntity customer, StoreEntity store
                , ReservationEntity reservation) {
            return ReviewEntity.builder()
                    .customer(customer)
                    .store(store)
                    .reservation(reservation)
                    .content(content)
                    .star(star)
                    .build();
        }
    }

    /**
     * 리뷰 작성 응답
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        /**
         * 리뷰 id
         */
        private Long reviewId;

        /**
         * 고객 id
         */
        private Long customerId;

        /**
         * 가게 id
         */
        private Long storeId;

        /**
         * 예약 id
         */
        private Long reservationId;

        /**
         * 리뷰 내용
         */
        private String content;

        /**
         * 별점
         */
        private BigDecimal star;

        /**
         * 엔티티로부터 Response 객체 생성
         *
         * @param review ReviewEntity 객체
         * @return Response 객체
         */
        public static Response from(ReviewEntity review) {
            return Response.builder()
                    .reviewId(review.getId())
                    .customerId(review.getCustomer().getId())
                    .storeId(review.getStore().getId())
                    .reservationId(review.getReservation().getId())
                    .content(review.getContent())
                    .star(review.getStar())
                    .build();
        }
    }

}
