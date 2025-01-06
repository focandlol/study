package focandlol.reservation.review.dto;

import focandlol.reservation.reservation.entity.ReservationEntity;
import focandlol.reservation.review.entity.ReviewEntity;
import focandlol.reservation.store.entity.StoreEntity;
import focandlol.reservation.customer.entity.CustomerEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    public static class Request{
        @NotNull
        private Long storeId;
        @NotNull
        private Long reservationId;

        @Size(max = 300)
        private String content;

        @NotNull
        private BigDecimal star;

        public ReviewEntity toEntity(CustomerEntity customer, StoreEntity store
                , ReservationEntity reservation){
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
    public static class Response{
        private Long reviewId;
        private Long customerId;
        private Long storeId;
        private Long reservationId;
        private String content;
        private BigDecimal star;

        public static Response from(ReviewEntity review){
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
