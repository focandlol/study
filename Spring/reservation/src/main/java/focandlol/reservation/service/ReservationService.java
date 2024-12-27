package focandlol.reservation.service;

import focandlol.reservation.dto.ReserveDto;
import focandlol.reservation.entity.StoreEntity;
import focandlol.reservation.entity.auth.CustomerEntity;
import focandlol.reservation.repository.CustomerRepository;
import focandlol.reservation.repository.ReservationRepository;
import focandlol.reservation.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;

    public ReserveDto.Response reserve(ReserveDto.Request request){
        CustomerEntity customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        StoreEntity store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        int reservedNumOfPeople = reservationRepository.findByDate(request.getDate()).stream()
                .map(a -> a.getNumOfPeople())
                .mapToInt(a -> a)
                .sum();

        if(store.getTotalSeat() <= reservedNumOfPeople + request.getNumOfPeople()){
            throw new RuntimeException("해당 날짜의 Reservation has already been reserved");
        }

        return ReserveDto.Response.from(reservationRepository.save(request.toEntity(store,customer)));
    }
}
