package focandlol.reservation.repository;

import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.type.ReservationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query("SELECT r FROM ReservationEntity r WHERE r.date = :date AND r.store.id = :storeId AND r.reservationType IN (:types)")
    List<ReservationEntity> findByDateAndStoreId(
            @Param("date") LocalDate date,
            @Param("storeId") Long storeId,
            @Param("types") List<ReservationType> types);

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
