package focandlol.reservation.dto;

import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.entity.StoreEntity;
import focandlol.reservation.entity.auth.CustomerEntity;
import focandlol.reservation.type.ReservationType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReserveDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        @NotNull
        private Long storeId;

        @NotNull
        private LocalDate date;

        @NotNull
        private LocalTime time;

        @NotNull
        private Integer NumOfPeople;

        public ReservationEntity toEntity(StoreEntity storeEntity, CustomerEntity customerEntity){
            return ReservationEntity.builder()
                    .store(storeEntity)
                    .customer(customerEntity)
                    .date(date)
                    .time(time)
                    .NumOfPeople(NumOfPeople)
                    .reservationType(ReservationType.UNAPPROVED)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private Long storeId;

        private Long customerId;

        private LocalDate date;

        private LocalTime time;

        private Integer NumOfPeople;

        private ReservationType reservationType;

        public static Response from(ReservationEntity reservationEntity){
            return Response.builder()
                    .storeId(reservationEntity.getStore().getId())
                    .customerId(reservationEntity.getCustomer().getId())
                    .date(reservationEntity.getDate())
                    .time(reservationEntity.getTime())
                    .NumOfPeople(reservationEntity.getNumOfPeople())
                    .reservationType(reservationEntity.getReservationType())
                    .build();
        }
    }
}
