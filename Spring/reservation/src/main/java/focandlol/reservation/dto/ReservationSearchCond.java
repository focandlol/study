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
    /**
     * 가게 이름으로 검색
     */
    private String storeName;

    /**
     * 날짜롤 필터링
     */
    private LocalDate date;

    /**
     * 정렬 조건
     */
    private ReservationSortType sortBy;

    /**
     * 예약 상태로 필터링
     */
    private ReservationType reservationType;

    /**
     * 오름차순 내림차순
     */
    private boolean ascending;
}
