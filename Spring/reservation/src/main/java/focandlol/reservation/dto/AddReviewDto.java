package focandlol.reservation.dto;

import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.entity.ReviewEntity;
import focandlol.reservation.entity.StoreEntity;
import focandlol.reservation.entity.auth.CustomerEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

public class AddReviewDto {

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
