package focandlol.resproject.review.service;

import focandlol.resproject.auth.dto.CustomUserDetails;
import focandlol.resproject.review.dto.AddReviewDto;
import focandlol.resproject.review.dto.UpdateReviewDto;

/**
 * 리뷰 서비스 인터페이스
 */
public interface ReviewService {
    AddReviewDto.Response addReview(CustomUserDetails user, AddReviewDto.Request request);

    UpdateReviewDto.Response updateReview(CustomUserDetails user, Long reviewId, UpdateReviewDto.Request request);

    void deleteReview(Long reviewId, CustomUserDetails user);
}
