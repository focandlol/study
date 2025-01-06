package focandlol.resproject.reservation.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 특정 날짜 남은 예약 가능 인원 dto
 */
@Getter
@Setter
public class ReservationNumMonth {

    /**
     * 예약 날짜
     */
    private LocalDate date;

    /**
     * 남은 예약 인원 수
     */
    private int leftSeat;

    public ReservationNumMonth(LocalDate date, int leftSeat) {
        this.date = date;
        this.leftSeat = leftSeat;
    }
}
