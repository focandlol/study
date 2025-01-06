package focandlol.reservation.reservation.dto;

import focandlol.reservation.reservation.type.ReservationSortType;
import focandlol.reservation.reservation.type.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 예약 검색 조건
 */
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
