package focandlol.reservation.repository;

import focandlol.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findByDateAndStoreId(LocalDate date,Long storeId);

    @Query("SELECT r FROM ReservationEntity r " +
            "JOIN FETCH r.customer c " +
            "JOIN FETCH r.store s " +
            "JOIN FETCH s.manager m " +
            "WHERE r.id = :reservationId")
    Optional<ReservationEntity> findByIdFetch(@Param("reservationId") Long reservationId);


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
}
