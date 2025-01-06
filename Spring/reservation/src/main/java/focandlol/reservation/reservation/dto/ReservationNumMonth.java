package focandlol.reservation.reservation.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationNumMonth {

    private LocalDate date;
    private int leftSeat;

    public ReservationNumMonth(LocalDate date, int leftSeat) {
        this.date = date;
        this.leftSeat = leftSeat;
    }
}
