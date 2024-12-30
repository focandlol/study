package focandlol.reservation.service;

import focandlol.reservation.dto.ReservationDto;
import focandlol.reservation.dto.ReservationSearchCond;
import focandlol.reservation.dto.ReservationUpdateDto;
import focandlol.reservation.dto.ReserveDto;
import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.entity.StoreEntity;
import focandlol.reservation.entity.auth.CustomerEntity;
import focandlol.reservation.repository.CustomerRepository;
import focandlol.reservation.repository.QueryReservationRepository;
import focandlol.reservation.repository.ReservationRepository;
import focandlol.reservation.repository.store.StoreRepository;
import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;
    private final QueryReservationRepository queryReservationRepository;

    public ReserveDto.Response reserve(ReserveDto.Request request){
        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        StoreEntity store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));


        List<ReservationEntity> byDateAndId = reservationRepository.findByDateAndStoreId(request.getDate(), store.getId());
        int sum = byDateAndId.stream()
                .map(a -> a.getNumOfPeople())
                .mapToInt(a -> a)
                .sum();
//        int reservedNumOfPeople = reservationRepository.findByDateAndId(request.getDate(),store.getId()).stream()
//                .map(a -> a.getNumOfPeople())
//                .mapToInt(a -> a)
//                .sum();

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

        List<ReservationEntity> byDateAndId = reservationRepository.findByDateAndStoreId(request.getDate(), reservation.getStore().getId());
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

    public List<ReservationDto> getReservationForCustomer(Long customerId, ReservationSearchCond reservationSearchCond
            , Pageable pageable){

        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return queryReservationRepository.findReservationForCustomer(customerId, reservationSearchCond, pageable)
                .stream().map(a -> ReservationDto.from(a))
                .collect(Collectors.toList());

    }
}
