package focandlol.reservation.dto;

import focandlol.reservation.type.ReservationSortType;
import focandlol.reservation.type.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationSearchCond {
    private String storeName;
    private LocalDate date;
    private ReservationSortType sortBy;
    private ReservationType reservationType;
    private boolean ascending;
}
