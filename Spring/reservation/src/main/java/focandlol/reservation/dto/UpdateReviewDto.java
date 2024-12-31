package focandlol.reservation.dto;

import focandlol.reservation.entity.ReviewEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

public class UpdateReviewDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        private String content;
        @NotNull
        private BigDecimal star;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long reviewId;
        private String content;
        private BigDecimal star;

        public static Response from(ReviewEntity review){
            return Response.builder()
                    .reviewId(review.getId())
                    .content(review.getContent())
                    .star(review.getStar())
                    .build();
        }
    }

}
