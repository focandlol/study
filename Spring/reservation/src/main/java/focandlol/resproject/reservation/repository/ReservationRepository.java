package focandlol.resproject.reservation.repository;

import focandlol.resproject.reservation.entity.ReservationEntity;
import focandlol.resproject.reservation.type.ReservationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * 예약 엔티티에 대한 repository 인터페이스
 */
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    /**
     * 특정 날짜, 가게 id 및 예약 상태 목록에 해당하는 예약을 조회
     *
     * @param date    예약 날짜
     * @param storeId 가게 ID
     * @param types   예약 상태 목록
     * @return 조건에 맞는 예약 목록
     */
    @Query("SELECT r FROM ReservationEntity r WHERE r.date = :date AND r.store.id = :storeId AND r.reservationType IN (:types)")
    List<ReservationEntity> findByDateAndStoreId(
            @Param("date") LocalDate date,
            @Param("storeId") Long storeId,
            @Param("types") List<ReservationType> types);

    /**
     * 예약 id 예약 정보를 조회
     *
     * @param reservationId 예약 id
     * @return 예약 정보 및 연관 엔티티를 포함한 Optional 객체
     */
    @Query("SELECT r FROM ReservationEntity r " +
            "JOIN FETCH r.customer c " +
            "JOIN FETCH r.store s " +
            "JOIN FETCH s.manager m " +
            "WHERE r.id = :reservationId")
    Optional<ReservationEntity> findByIdFetch(@Param("reservationId") Long reservationId);

    /**
     * 특정 사용자 이름, 가게 id, 날짜, 시간에 해당하는 예약을 조회
     *
     * @param username 사용자 이름
     * @param storeId  가게 id
     * @param date     예약 날짜
     * @param time     예약 시간
     * @return 조건에 맞는 예약 정보 Optional 객체
     */
    @Query("SELECT r FROM ReservationEntity r " +
            "JOIN FETCH r.customer c " +
            "WHERE c.username = :username " +
            "AND r.store.id = :storeId " +
            "AND r.date = :date " +
            "AND r.time = :time")
    Optional<ReservationEntity> findReservation(
            @Param("username") String username,
            @Param("storeId") Long storeId,
            @Param("date") LocalDate date,
            @Param("time") LocalTime time);

    /**
     * 특정 가게 id와 날짜 범위에 해당하는 예약 목록을 조회
     *
     * @param storeId   가게 id
     * @param startDate 시작 날짜
     * @param endDate   종료 날짜
     * @return 조건에 맞는 예약 목록
     */
    @Query("SELECT r FROM ReservationEntity r " +
            "JOIN FETCH r.store " +
            "WHERE r.store.id = :storeId " +
            "AND r.date BETWEEN :startDate AND :endDate")
    List<ReservationEntity> findByStoreIdAndDateRangeFetch(
            @Param("storeId") Long storeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
