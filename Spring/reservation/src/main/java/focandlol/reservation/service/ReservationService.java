package focandlol.reservation.service;

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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public ReserveDto.Response reserve(ReserveDto.Request request){
        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        StoreEntity store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));


        List<ReservationEntity> byDateAndId = reservationRepository.findByDateAndStoreId(request.getDate(), store.getId()
                , Arrays.asList(UNAPPROVED,APPROVED));
        int sum = byDateAndId.stream()
                .map(a -> a.getNumOfPeople())
                .mapToInt(a -> a)
                .sum();

        System.out.println(sum);
        System.out.println("list.size" + byDateAndId.size());

        if(store.getTotalSeat() < sum + request.getNumOfPeople()){
            throw new RuntimeException("해당 날짜의 Reservation has already been reserved");
        }

        return ReserveDto.Response.from(reservationRepository.save(request.toEntity(store,customer)));
    }

    public ReservationUpdateDto.Response update(Long reservationId, ReservationUpdateDto.Request request){
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if(!reservation.getCustomer().getId().equals(request.getCustomerId())){
            throw new RuntimeException("another people");
        }

        List<ReservationEntity> byDateAndId = reservationRepository.findByDateAndStoreId(request.getDate(), reservation.getStore().getId()
                , Arrays.asList(UNAPPROVED,APPROVED));
        int sum = byDateAndId.stream()
                .map(a -> a.getNumOfPeople())
                .mapToInt(a -> a)
                .sum();


        if(reservation.getDate().isEqual(request.getDate())){
            if(reservation.getStore().getTotalSeat()
                    < sum + request.getNumOfPeople() - reservation.getNumOfPeople()){
                throw new RuntimeException("변경하려는 날짜의 예약이 다 찼습니다.");
            }
        }else{
            if(reservation.getStore().getTotalSeat() < sum + request.getNumOfPeople()){
                throw new RuntimeException("해당 날짜의 Reservation has already been reserved");
            }
        }

        reservation.setDate(request.getDate());
        reservation.setTime(request.getTime());
        reservation.setNumOfPeople(request.getNumOfPeople());

        return ReservationUpdateDto.Response.from(reservation);
    }

    public ReservationStatusDto.Response changeStatus(Long reservationId, ReservationStatusDto.Request request){
        ReservationEntity reservation = reservationRepository.findByIdFetch(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        ManagerEntity manager = managerRepository.findById(request.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        if(!reservation.getStore().getManager().getId().equals(manager.getId())){
            throw new RuntimeException("another manager");
        }

        if(reservation.getReservationType() == request.getReservationType()){
            throw new RuntimeException("same type");
        }

        reservation.setReservationType(request.getReservationType());

        return ReservationStatusDto.Response.from(reservation);
    }

    public ReservationArriveDto.Response arrive(ReservationArriveDto.Request request){
        CustomerEntity customer = customerRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        StoreEntity store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        ReservationEntity reservation = reservationRepository.findReservation(request.getUsername(),
                        request.getStoreId(), request.getDate(), request.getTime())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if(reservation.getReservationType() != APPROVED){
            throw new RuntimeException("unapproved reservation");
        }

        if(LocalDateTime.now().isAfter(reservation.getLocalDateTime().minusMinutes(10))){
            throw new RuntimeException("time is over");
        }

        reservation.setReservationType(VISITED);

        return ReservationArriveDto.Response.from(reservation);
    }

    public List<ReservationDto> getReservationForCustomer(Long customerId, ReservationSearchCond reservationSearchCond
            , Pageable pageable){

        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return queryReservationRepository.findReservationForCustomer(customerId, reservationSearchCond, pageable)
                .stream().map(a -> ReservationDto.from(a))
                .collect(Collectors.toList());

    }

    public List<ReservationDto> getReservationForManager(Long managerId, ReservationSearchCond reservationSearchCond,
                                                         Pageable pageable){

        ManagerEntity manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        return queryReservationRepository.findReservationForManager(managerId, reservationSearchCond, pageable)
                .stream().map(a -> ReservationDto.from(a))
                .collect(Collectors.toList());
    }
}
