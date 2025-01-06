package focandlol.resproject.review.dto;

import focandlol.resproject.review.entity.ReviewEntity;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
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
    public static class Request {
        /**
         * 리뷰 내용
         */
        private String content;

        /**
         * 별점
         */
        @NotNull
        @Digits(integer = 1, fraction = 1)
        @DecimalMin("0.0")
        @DecimalMax("5.0")
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
    public static class Response {
        /**
         * 리뷰 id
         */
        private Long reviewId;

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
                    .content(review.getContent())
                    .star(review.getStar())
                    .build();
        }
    }

}
