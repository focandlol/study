package focandlol.reservation.controller;

import focandlol.reservation.dto.AddReviewDto;
import focandlol.reservation.dto.CustomUserDetails;
import focandlol.reservation.dto.UpdateReviewDto;
import focandlol.reservation.service.ReservationService;
import focandlol.reservation.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> review(@RequestBody AddReviewDto.Request request){
        return ResponseEntity.ok().body(reviewService.addReview(request));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> update(
            @PathVariable(name = "id") Long reviewId,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid UpdateReviewDto.Request request){
        return ResponseEntity.ok().body(reviewService.updateReview(userDetails.getUserDetailsDto().getId()
                , reviewId,request));
    }

    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('CUSTOMER') or hasRole('MANAGER')")
    public void delete(@PathVariable(name = "id") Long reviewId
            , @AuthenticationPrincipal CustomUserDetails user){
        reviewService.deleteReview(reviewId,user);
    }
}
