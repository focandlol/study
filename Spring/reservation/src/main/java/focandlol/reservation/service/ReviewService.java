package focandlol.reservation.service;

import focandlol.reservation.exception.CustomException;
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
import focandlol.reservation.type.ReviewUpdateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static focandlol.reservation.exception.ErrorCode.*;
import static focandlol.reservation.type.ReviewUpdateType.*;

/**
 * 리뷰 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;

    /**
     * 리뷰 작성
     * @param user : 현재 로그인한 사용자 정보
     * @param request : 리뷰 작성에 필요한 정보
     * @return : 작성된 리뷰 정보
     */
    public AddReviewDto.Response addReview(CustomUserDetails user, AddReviewDto.Request request) {
        /**
         * 리뷰 작성하려는 고객 조회
         */
        CustomerEntity customer = getCustomerOrElseThrow(user);

        /**
         * 가게가 존재하는지 확인
         */
        StoreEntity store = getStoreOrElseThrow(request);

        /**
         * 예약이 존재하는지 확인
         */
        ReservationEntity reservation = getReservationOrElseThrow(request);

        /**
         * 리뷰 작성 예외 처리
         */
        validateAddReview(reservation, customer, store);

        /**
         * 가게 별점 업데이트
         */
        updateStar(request.getStar(), store, ADD, BigDecimal.valueOf(0.0));
        return AddReviewDto.Response.from(
                reviewRepository.save(request.toEntity(customer,store,reservation)));
    }

    /**
     * 리뷰 수정
     * @param user : 현재 사용중인 사용자 정보
     * @param reviewId : 수정할 리뷰 id
     * @param request : 리뷰 수정에 필요한 정보
     * @return : 수정된 리뷰 정보
     */
    public UpdateReviewDto.Response updateReview(CustomUserDetails user, Long reviewId, UpdateReviewDto.Request request) {
        /**
         * 리뷰가 존재하는지 확인
         */
        ReviewEntity review = getReviewOrElseThrow(reviewId);

        /**
         * 리뷰 작성자와 현재 로그인한 사용자가 동일한지 확인
         */
        if(review.getCustomer().getId() != user.getId()) {
            throw new CustomException(ANOTHER_CUSTOMER);
        }

        /**
         * 가게 별점 업데이트
         */
        updateStar(request.getStar(), review.getStore(), UPDATE, review.getStar());

        review.setContent(request.getContent());
        review.setStar(request.getStar());

        return UpdateReviewDto.Response.from(review);
    }

    /**
     * 리뷰 삭제
     * @param reviewId : 삭제할 리뷰 id
     * @param user : 현재 로그인한 사용자 정보
     */
    public void deleteReview(Long reviewId, CustomUserDetails user){
        /**
         * 리뷰가 존재하는지 확인
         */
        ReviewEntity review = getReviewOrElseThrow(reviewId);

        /**
         * 리뷰 삭제 예외 처리
         */
        validateDeleteReview(user, user.getUserDetailsDto().getRoles(), review);

        updateStar(review.getStar(),review.getStore(),DELETE, review.getStar());
        reviewRepository.deleteById(reviewId);
    }

    /**
     * 리뷰 삭제 예외 처리
     */
    private static void validateDeleteReview(CustomUserDetails user, List<String> roles, ReviewEntity review) {
        /**
         * role에 따라서 검증
         * 로그인한 사용자가 manager라면 가게 주인과 현재 로그인한 사용자가 동일한지 확인
         * 로그인한 사용자가 customer라면 리뷰 작성자와 현재 로그인한 사용자가 동일한지 확인
         */
        if(roles.contains("ROLE_MANAGER")){
            if(user.getId() != review.getStore().getManager().getId()){
                throw new CustomException(ANOTHER_MANAGER);
            }
        }else if(roles.contains("ROLE_CUSTOMER")){
            if(user.getId() != review.getCustomer().getId()){
                System.out.println("Customer id mismatch");
                throw new CustomException(ANOTHER_CUSTOMER);
            }
        }
    }

    /**
     * 리뷰 작성 예외 처리
     */
    private static void validateAddReview(ReservationEntity reservation, CustomerEntity customer, StoreEntity store) {
        /**
         * 리뷰 작성자가 현재 로그인한 사용자와 동일한지 확인
         */
        if(reservation.getCustomer().getId() != customer.getId()) {
            throw new CustomException(ANOTHER_CUSTOMER);
        }

        /**
         * 예약된 가게와 동일한지 확인
         */
        if(reservation.getStore().getId() != store.getId()) {
            throw new CustomException(ANOTHER_STORE);
        }

        /**
         * 가게를 방문을 완료하였는지 확인
         */
        if(reservation.getReservationType() != ReservationType.VISITED){
            throw new CustomException(NOT_VISITED);
        }
    }

    /**
     * 예액을 조회하고 만약 예약이 없다면 예외 발생
     */
    private ReservationEntity getReservationOrElseThrow(AddReviewDto.Request request) {
        return reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));
    }

    /**
     * 가게를 조회하고 만약 가게가 없다면 예외 발생
     */
    private StoreEntity getStoreOrElseThrow(AddReviewDto.Request request) {
        return storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));
    }

    /**
     * 고객을 조회하고 만약 고객이 없다면 예외 발생
     */
    private CustomerEntity getCustomerOrElseThrow(CustomUserDetails user) {
        return customerRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(CUSTOMER_NOT_FOUND));
    }

    /**
     * 리뷰를 조회하고 만약 리뷰가 없다면 예외 발생
     */
    private ReviewEntity getReviewOrElseThrow(Long reviewId) {
        return reviewRepository.findByIdFetch(reviewId)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_FOUND));
    }

    /**
     * 가게 별점 정보 업데이트
     */
    private void updateStar(BigDecimal newStar, StoreEntity store, ReviewUpdateType type
            ,BigDecimal beforeStar) {
        /**
         * 해당 가게 리뷰 개수 조회
         */
        long count = reviewRepository.countByStoreId(store.getId());

        /**
         * type == ADD : 리뷰 추가 -> (현재 평균 별점 * 리뷰 개수) + 새로운 별점, 리뷰 개수 + 1
         * type == UPDATE : 리뷰 업데이트 -> (현재 평균 별점 * 리뷰 개수) - 기존 별점 + 새로운 별점, 리뷰 개수 그대로
         * type == DELETE : 리뷰 삭제 -> (현재 평균 별점 * 리뷰 개수) - 삭제할 별점, 리뷰 개수 -1
         */
        BigDecimal change = null;
        if(type == ADD){
            change = store.getStar().multiply(BigDecimal.valueOf(count)).add(newStar);
            count++;
        }else if(type == UPDATE){
            change = store.getStar().multiply(BigDecimal.valueOf(count)).subtract(beforeStar).add(newStar);
        }else if(type == DELETE){
            change = store.getStar().multiply(BigDecimal.valueOf(count)).subtract(beforeStar);
            count--;
        }

        /**
         * 해당 가게 새로운 별점 계산
         */
        BigDecimal updatedStar = change
                .divide(BigDecimal.valueOf(count), 1, RoundingMode.HALF_UP);

        store.setStar(updatedStar);
    }
}
