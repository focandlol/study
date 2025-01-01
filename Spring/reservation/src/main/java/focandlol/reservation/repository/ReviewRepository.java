package focandlol.reservation.repository;

import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query("SELECT r FROM ReviewEntity r " +
            "JOIN FETCH r.customer c " +
            "JOIN FETCH r.store s " +
            "WHERE r.id = :reviewId")
    Optional<ReviewEntity> findByIdFetch(@Param("reviewId") Long reviewId);
}
