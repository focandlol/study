package focandlol.reservation.service;

import focandlol.exception.CustomException;
import focandlol.exception.ErrorCode;
import focandlol.reservation.dto.AddReviewDto;
import focandlol.reservation.dto.CustomUserDetails;
import focandlol.reservation.dto.UpdateReviewDto;
import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.entity.ReviewEntity;
import focandlol.reservation.entity.StoreEntity;
import focandlol.reservation.entity.auth.CustomerEntity;
import focandlol.reservation.repository.CustomerRepository;
import focandlol.reservation.repository.ReservationRepository;
import focandlol.reservation.repository.ReviewRepository;
import focandlol.reservation.repository.store.StoreRepository;
import focandlol.reservation.type.ReservationType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static focandlol.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;

    public AddReviewDto.Response addReview(AddReviewDto.Request request) {
        System.out.println(request.getCustomerId());
        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        StoreEntity store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        ReservationEntity reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if(reservation.getCustomer().getId() != customer.getId()) {
            throw new RuntimeException("Customer id mismatch");
        }

        if(reservation.getStore().getId() != store.getId()) {
            throw new RuntimeException("Store id mismatch");
        }

        if(reservation.getReservationType() != ReservationType.VISITED){
            throw new RuntimeException("Reservation type mismatch");
        }

        return AddReviewDto.Response.from(
                reviewRepository.save(request.toEntity(customer,store,reservation)));
    }

    public UpdateReviewDto.Response updateReview(Long customerId, Long reviewId, UpdateReviewDto.Request request) {
        System.out.println(customerId);
        ReviewEntity review = getReviewOrElseThrow(reviewId);

        if(review.getCustomer().getId() != customerId) {
            throw new RuntimeException("Customer id mismatch");
        }

        review.setContent(request.getContent());
        review.setStar(request.getStar());

        return UpdateReviewDto.Response.from(review);
    }

    public void deleteReview(Long reviewId, CustomUserDetails user){
        ReviewEntity review = getReviewOrElseThrow(reviewId);

        List<String> roles = user.getUserDetailsDto().getRoles();

        if(roles.contains("ROLE_MANAGER")){
            if(user.getId() != review.getStore().getManager().getId()){
                throw new CustomException(ANOTHER_MANAGER);
            }
        }else if(roles.contains("ROLE_CUSTOMER")){
            if(user.getId() != review.getCustomer().getId()){
                throw new CustomException(ANOTHER_CUSTOMER);
            }
        }
        reviewRepository.deleteById(reviewId);
    }

    private ReviewEntity getReviewOrElseThrow(Long reviewId) {
        return reviewRepository.findByIdFetch(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }
}
