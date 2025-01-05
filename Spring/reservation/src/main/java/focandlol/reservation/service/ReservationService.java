package focandlol.reservation.service;

import focandlol.reservation.exception.CustomException;
import focandlol.reservation.dto.*;
import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.entity.StoreEntity;
import focandlol.reservation.entity.auth.CustomerEntity;
import focandlol.reservation.repository.CustomerRepository;
import focandlol.reservation.repository.ManagerRepository;
import focandlol.reservation.repository.QueryReservationRepository;
import focandlol.reservation.repository.ReservationRepository;
import focandlol.reservation.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static focandlol.reservation.exception.ErrorCode.*;
import static focandlol.reservation.type.ReservationType.*;

/**
 * 예약 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;
    private final QueryReservationRepository queryReservationRepository;
    private final ManagerRepository managerRepository;

    /**
     * 예약
     * @param user : 로그인한 사용자 정보
     * @param request : 예약에 필요한 정보
     * @return : 예약 정보
     */
    public ReserveDto.Response reserve(CustomUserDetails user, ReserveDto.Request request){
        /**
         * 예약하려는 고객 조회
         */
        CustomerEntity customer = getCustomerOrElseThrow(user.getId());

        /**
         * 가게가 존재하는지 확인
         */
        StoreEntity store = getStoreOrElseThrow(request);

        /**
         * 예약이 가능한지 확인
         */
        checkCanReserve(request, store);

        return ReserveDto.Response.from(reservationRepository.save(request.toEntity(store,customer)));
    }

    /**
     * 예약 수정
     * @param reservationId : 수정할 예약 id
     * @param user : 현재 로그인한 사용자 정보
     * @param request : 수정에 필요한 정보
     * @return : 수정된 예약 정보
     */
    public ReservationUpdateDto.Response update(Long reservationId, CustomUserDetails user,ReservationUpdateDto.Request request){
        /**
         * 예약이 존재하는지 확인
         */
        ReservationEntity reservation = getReservationOrElseThrow(reservationId);

        /**
         * 예약 수정 예외 처리
         */
        validateUpdateReservation(user, request, reservation);

        reservation.setDate(request.getDate());
        reservation.setTime(request.getTime());
        reservation.setNumOfPeople(request.getNumOfPeople());

        return ReservationUpdateDto.Response.from(reservation);
    }

    /**
     * 예약 상태 변경
     * @param reservationId : 상태를 변경할 예약 id
     * @param user : 현재 로그인한 사용자 정보
     * @param request : 변경할 상태 정보
     * @return
     */
    public ReservationStatusDto.Response changeStatus(Long reservationId, CustomUserDetails user,
                                                      ReservationStatusDto.Request request){
        /**
         * 예약이 존재하는지 확인
         */
        ReservationEntity reservation = getReservationOrElseThrow(reservationId);

        /**
         * 예약 상태 변경 예외 처리
         */
        validateChangeStatus(user, request, reservation);

        reservation.setReservationType(request.getReservationType());

        return ReservationStatusDto.Response.from(reservation);
    }

    /**
     * 키오스크 도착
     * @param request : 예약 확인에 필요한 정보
     * @return
     */
    public ReservationArriveDto.Response arrive(ReservationArriveDto.Request request){
        /**
         * 해당 store에 예약자 email, date, time이 일치하는 예약이 있는지 확인
         */
        ReservationEntity reservation = reservationRepository.findReservation(request.getUsername(),
                        request.getStoreId(), request.getDate(), request.getTime())
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        /**
         * 도착 예외 처리
         */
        validationArrive(reservation);

        reservation.setReservationType(VISITED);

        return ReservationArriveDto.Response.from(reservation);
    }

    /**
     * customer 자신이 예약한 리스트
     * @param user : 현재 로그인한 사용자 정보
     * @param reservationSearchCond : 예약 검색 조건
     * reservationSearchCond.storeName : 가게 이름으로 검색
     * reservationSearchCond.date : 날짜로 검색
     * reservationSearchCond.sortBy : 정렬 조건
     * STORE_NAME : 가게 이름으로 정렬
     * DATE : 날짜로 정렬
     * TIME : 시간으로 정렬
     * reservationSearchCond.reservationType : 예약 상태로 검색
     * UNAPPROVED : 예약을 했지만 아직 가게 주인 승인이 되지 않은 상태
     * APPROVED : 가게 주인이 승인한 상태
     * VISITED : 방문 완료한 상태
     * CANCELED : 예약이 취소된 상태
     * reservation.isAscending : 오름차순 내림차순
     * @param pageable : 페이징
     * 동적 퀴리 생성을 위해 querydsl 사용하는 queryReviewRepository 사용
     * @return : 조건에 따라 검색된 해당 고객이 예약한 리스트
     */
    public List<ReservationDto> getReservationForCustomer(CustomUserDetails user, ReservationSearchCond reservationSearchCond
            , Pageable pageable){
        return queryReservationRepository.findReservationForCustomer(user.getId(), reservationSearchCond, pageable)
                .stream().map(a -> ReservationDto.from(a))
                .collect(Collectors.toList());

    }

    /**
     * manager 자신이 관리하는 가게들의 예약 리스트
     * @param user : 현재 로그인한 사용자 정보
     * @param reservationSearchCond : 예약 검색 조건
     * reservationSearchCond.storeName : 가게 이름으로 검색
     * reservationSearchCond.date : 날짜로 검색
     * reservationSearchCond.sortBy : 정렬 조건
     * STORE_NAME : 가게 이름으로 정렬
     * DATE : 날짜로 정렬
     * TIME : 시간으로 정렬
     * reservationSearchCond.reservationType : 예약 상태로 검색
     * UNAPPROVED : 예약을 했지만 아직 가게 주인 승인이 되지 않은 상태
     * APPROVED : 가게 주인이 승인한 상태
     * VISITED : 방문 완료한 상태
     * CANCELED : 예약이 취소된 상태
     * reservation.isAscending : 오름차순 내림차순
     * @param pageable : 페이징
     * 동적 퀴리 생성을 위해 querydsl 사용하는 queryReviewRepository 사용
     * @return : 조건에 따라 검색된 해당 manager가 관리하는 가게의 예약 리스트
     */
    public List<ReservationDto> getReservationForManager(CustomUserDetails user, ReservationSearchCond reservationSearchCond,
                                                         Pageable pageable){
        return queryReservationRepository.findReservationForManager(user.getId(), reservationSearchCond, pageable)
                .stream().map(a -> ReservationDto.from(a))
                .collect(Collectors.toList());
    }

    /**
     * 고객을 조회하고 만약 고객이 없으면 예외 발생
     */
    private CustomerEntity getCustomerOrElseThrow(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomException(CUSTOMER_NOT_FOUND));
    }

    /**
     * 가게를 조회하고 만약 가게가 없으면 예외 발생
     */
    private StoreEntity getStoreOrElseThrow(ReserveDto.Request request) {
        return storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));
    }

    /**
     * 예약을 조회하고 만약 예약이 없으면 예외 발생
     */
    private ReservationEntity getReservationOrElseThrow(Long reservationId) {
        return reservationRepository.findByIdFetch(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));
    }

    /**
     * @return : 해당 가게에 해당 날짜에 예약한 고객들의 수
     */
    private int reservePeopleNumInDate(LocalDate date, StoreEntity store){
        return reservationRepository.findByDateAndStoreId(date, store.getId()
                , Arrays.asList(UNAPPROVED,APPROVED)).stream()
                .map(a -> a.getNumOfPeople())
                .mapToInt(a -> a)
                .sum();

    }

    /**
     * 예약 수정 예외 처리
     */
    private void validateUpdateReservation(CustomUserDetails user, ReservationUpdateDto.Request request, ReservationEntity reservation) {
        /**
         * 예약자와 현재 로그인한 사용자가 동일한지 확인
         */
        if(!reservation.getCustomer().getId().equals(user.getId())){
            throw new CustomException(ANOTHER_CUSTOMER);
        }

        /**
         * 해당 가게에 해당 날짜에 예약한 고객들의 수 가져오기
         */
        int peopleSum = reservePeopleNumInDate(request.getDate(), reservation.getStore());

        /**
         * 예약 변경이 가능한지 검증
         */
        checkCanUpdate(request, reservation, peopleSum);
    }

    /**
     * 예약 상태 변경 예외 처리
     */
    private static void validateChangeStatus(CustomUserDetails user, ReservationStatusDto.Request request, ReservationEntity reservation) {
        /**
         * 가게 주인과 현재 로그인한 사용자가 동일한지 확인
         */
        if(!reservation.getStore().getManager().getId().equals(user.getId())){
            throw new CustomException(ANOTHER_MANAGER);
        }

        /**
         * 현재 예약 상태가 바꾸려는 예약 상태와 동일한지 확인
         * 동일하다면 예외 발생
         */
        if(reservation.getReservationType() == request.getReservationType()){
            throw new CustomException(RESERVATION_SAME_TYPE);
        }
    }

    /**
     * 키오스크 도착 예외 처리
     */
    private static void validationArrive(ReservationEntity reservation) {
        /**
         * 예약 상태가 APPROVED(승인된 예약) 상태인지 확인
         */
        if(reservation.getReservationType() != APPROVED){
            throw new CustomException(RESERVATION_IS_NOT_APPROVE);
        }

        /**
         * 도착 시간이 예약 10분 전인지 확인
         */
        if(LocalDateTime.now().isAfter(reservation.getLocalDateTime().minusMinutes(10))){
            throw new CustomException(ARRIVE_TIME_IS_OVER);
        }

        /**
         * 예약시간 보다 너무 빨리 도착하면 안되니까 예약 시간 30분전 이후부터 예약 확인 가능
         * 즉 예약시간 30분 전 ~ 10분 전까지 예약 확인 가능
         */
        if (LocalDateTime.now().isBefore(reservation.getLocalDateTime().minusMinutes(30))) {
            throw new CustomException(ARRIVE_TOO_EARLY);
        }
    }

    /**
     * 예약이 가능한지 확인 (공통 로직)
     */
    private static void validateReservationCapacity(int totalSeat, int reservationSum) {
        /**
         * 가게 총 예약 가능 인원수가 (이전 예약자 수 + 현재 요청 예약자 수) 보다 작으면 예외
         */
        if (totalSeat < reservationSum) {
            throw new CustomException(RESERVATION_IS_OVER);
        }
    }

    /**
     * 예약이 가능한지 확인
     */
    private void checkCanReserve(ReserveDto.Request request, StoreEntity store) {
        /**
         * 해당 날짜에 예약한 고객 수
         */
        int peopleSum = reservePeopleNumInDate(request.getDate(), store);

        /**
         * 공통 메서드를 사용하여 예약 가능 여부 확인
         * peopleSum + request.getNumOfPeople : 이미 예약한 고객 수 + 현재 요청 예약자 수
         * */
        validateReservationCapacity(store.getTotalSeat(), peopleSum + request.getNumOfPeople());
    }

    /**
     * 예약 수정 가능한지 확인
     */
    private static void checkCanUpdate(ReservationUpdateDto.Request request, ReservationEntity reservation, int peopleSum) {
        /**
         * 예약할 가게의 총 예약 가능 인원수
         */
        int totalSeat = reservation.getStore().getTotalSeat();

        /**
         * 원래 예약일과 같은 날짜에 인원수만 변경하고자 할 때
         * peopleSum + request.getNumOfPeople() - reservation.getNumOfPeople()
         * 이미 예약한 고객 수 + 현재 요청 예약자 수(변경할 예약자 수) - 이전 예약 인원수
         * 공통 메서드를 사용하여 예약 가능 여부 확인
         */
        if (reservation.getDate().isEqual(request.getDate())) {
            int reservationSum = peopleSum + request.getNumOfPeople() - reservation.getNumOfPeople();
            validateReservationCapacity(totalSeat, reservationSum);
        }
        /**
         * 원래 예약일과 다른 날짜로 예약일을 변경하고자 할 때
         * peopleSum + request.getNumOfPeople()
         * 이미 예약한 고객 수 + 현재 요청 예약자 수
         * 공통 메서드를 사용하여 예약 가능 여부 확인
         */
        else {
            validateReservationCapacity(totalSeat, peopleSum + request.getNumOfPeople());
        }
    }


}
