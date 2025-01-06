package focandlol.reservation.review.dto;

import focandlol.reservation.review.entity.ReviewEntity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

/**
 * 리뷰 수정
 */
public class UpdateReviewDto {

    /**
     * 리뷰 수정 요청
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        private String content;
        @NotNull
        @Digits(integer = 1, fraction = 1)
        @Min(0)
        @Max(5)
        private BigDecimal star;
    }

    /**
     * 리뷰 수정 응답
     */
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
