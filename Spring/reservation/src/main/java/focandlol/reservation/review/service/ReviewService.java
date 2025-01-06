package focandlol.reservation.review.service;

import focandlol.reservation.auth.dto.CustomUserDetails;
import focandlol.reservation.review.dto.AddReviewDto;
import focandlol.reservation.review.dto.UpdateReviewDto;

public interface ReviewService {
    AddReviewDto.Response addReview(CustomUserDetails user, AddReviewDto.Request request);

    UpdateReviewDto.Response updateReview(CustomUserDetails user, Long reviewId, UpdateReviewDto.Request request);

    void deleteReview(Long reviewId, CustomUserDetails user);
}
