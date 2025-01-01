package focandlol.reservation.service;

import focandlol.exception.CustomException;
import focandlol.exception.ErrorCode;
import focandlol.reservation.dto.*;
import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.entity.StoreEntity;
import focandlol.reservation.entity.auth.CustomerEntity;
import focandlol.reservation.entity.auth.ManagerEntity;
import focandlol.reservation.repository.CustomerRepository;
import focandlol.reservation.repository.ManagerRepository;
import focandlol.reservation.repository.QueryReservationRepository;
import focandlol.reservation.repository.ReservationRepository;
import focandlol.reservation.repository.store.StoreRepository;
import focandlol.reservation.type.ReservationType;
import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static focandlol.exception.ErrorCode.*;
import static focandlol.reservation.type.ReservationType.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;
    private final QueryReservationRepository queryReservationRepository;
    private final ManagerRepository managerRepository;

    public ReserveDto.Response reserve(Long customerId, ReserveDto.Request request){
        CustomerEntity customer = getCustomerOrElseThrow(customerId);
        StoreEntity store = getStoreOrElseThrow(request);

        int peopleSum = reservePeopleNumInDate(request.getDate(),store);

        if(store.getTotalSeat() < peopleSum + request.getNumOfPeople()){
            throw new CustomException(RESERVATION_IS_OVER);
        }

        return ReserveDto.Response.from(reservationRepository.save(request.toEntity(store,customer)));
    }

    public ReservationUpdateDto.Response update(Long reservationId, Long customerId,ReservationUpdateDto.Request request){
        ReservationEntity reservation = getReservationOrElseThrow(reservationId);

        if(!reservation.getCustomer().getId().equals(customerId)){
            throw new CustomException(ANOTHER_CUSTOMER);
        }

        int peopleSum = reservePeopleNumInDate(request.getDate(),reservation.getStore());

        if(reservation.getDate().isEqual(request.getDate())){
            if(reservation.getStore().getTotalSeat()
                    < peopleSum + request.getNumOfPeople() - reservation.getNumOfPeople()){
                throw new CustomException(RESERVATION_IS_OVER);
            }
        }else{
            if(reservation.getStore().getTotalSeat() < peopleSum + request.getNumOfPeople()){
                throw new CustomException(RESERVATION_IS_OVER);
            }
        }

        reservation.setDate(request.getDate());
        reservation.setTime(request.getTime());
        reservation.setNumOfPeople(request.getNumOfPeople());

        return ReservationUpdateDto.Response.from(reservation);
    }

    public ReservationStatusDto.Response changeStatus(Long reservationId, Long managerId,
                                                      ReservationStatusDto.Request request){
        ReservationEntity reservation = getReservationOrElseThrow(reservationId);

        if(!reservation.getStore().getManager().getId().equals(managerId)){
            throw new CustomException(ANOTHER_MANAGER);
        }

        if(reservation.getReservationType() == request.getReservationType()){
            throw new CustomException(RESERVATION_SAME_TYPE);
        }

        reservation.setReservationType(request.getReservationType());

        return ReservationStatusDto.Response.from(reservation);
    }

    public ReservationArriveDto.Response arrive(ReservationArriveDto.Request request){
        ReservationEntity reservation = reservationRepository.findReservation(request.getUsername(),
                        request.getStoreId(), request.getDate(), request.getTime())
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        if(reservation.getReservationType() != APPROVED){
            throw new CustomException(RESERVATION_IS_NOT_APPROVE);
        }

        if(LocalDateTime.now().isAfter(reservation.getLocalDateTime().minusMinutes(10))){
            throw new CustomException(ARRIVE_TIME_IS_OVER);
        }

        reservation.setReservationType(VISITED);

        return ReservationArriveDto.Response.from(reservation);
    }

    public List<ReservationDto> getReservationForCustomer(Long customerId, ReservationSearchCond reservationSearchCond
            , Pageable pageable){
        return queryReservationRepository.findReservationForCustomer(customerId, reservationSearchCond, pageable)
                .stream().map(a -> ReservationDto.from(a))
                .collect(Collectors.toList());

    }

    public List<ReservationDto> getReservationForManager(Long managerId, ReservationSearchCond reservationSearchCond,
                                                         Pageable pageable){
        return queryReservationRepository.findReservationForManager(managerId, reservationSearchCond, pageable)
                .stream().map(a -> ReservationDto.from(a))
                .collect(Collectors.toList());
    }

    private CustomerEntity getCustomerOrElseThrow(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomException(CUSTOMER_NOT_FOUND));
    }

    private StoreEntity getStoreOrElseThrow(ReserveDto.Request request) {
        return storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));
    }

    private ReservationEntity getReservationOrElseThrow(Long reservationId) {
        return reservationRepository.findByIdFetch(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));
    }

    private int reservePeopleNumInDate(LocalDate date, StoreEntity store){
        return reservationRepository.findByDateAndStoreId(date, store.getId()
                , Arrays.asList(UNAPPROVED,APPROVED)).stream()
                .map(a -> a.getNumOfPeople())
                .mapToInt(a -> a)
                .sum();

    }
}
