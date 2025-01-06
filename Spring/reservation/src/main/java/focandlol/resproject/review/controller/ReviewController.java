package focandlol.resproject.review.controller;

import focandlol.resproject.auth.dto.CustomUserDetails;
import focandlol.resproject.review.dto.AddReviewDto;
import focandlol.resproject.review.dto.UpdateReviewDto;
import focandlol.resproject.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 리뷰 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 추가
     *
     * @param user    : 현재 로그인한 사용자 정보
     * @param request : 리뷰 작성을 위한 정보
     * @return : 작성한 리뷰 정보
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> review(@AuthenticationPrincipal CustomUserDetails user,
                                    @RequestBody @Valid AddReviewDto.Request request) {
        return ResponseEntity.ok().body(reviewService.addReview(user, request));
    }

    /**
     * 리뷰 수정
     *
     * @param reviewId : 수정할 리뷰 id
     * @param user     : 현재 로그인한 사용자 정보
     * @param request  : 리뷰 수정을 위한 정보
     * @return : 수정된 리뷰 정보
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> update(
            @PathVariable(name = "id") Long reviewId,
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody @Valid UpdateReviewDto.Request request) {
        return ResponseEntity.ok().body(reviewService.updateReview(user, reviewId, request));
    }

    /**
     * 삭제할 리뷰 졍보
     *
     * @param reviewId : 삭제할 리뷰 id
     * @param user     : 현재 로그인한 사용자 정보
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('MANAGER')")
    public void delete(@PathVariable(name = "id") Long reviewId
            , @AuthenticationPrincipal CustomUserDetails user) {
        reviewService.deleteReview(reviewId, user);
    }
}
