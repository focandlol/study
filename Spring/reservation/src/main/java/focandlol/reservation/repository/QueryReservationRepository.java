package focandlol.reservation.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import focandlol.reservation.dto.ReservationSearchCond;
import focandlol.reservation.dto.store.StoreSearchCond;
import focandlol.reservation.entity.QReservationEntity;
import focandlol.reservation.entity.QStoreEntity;
import focandlol.reservation.entity.ReservationEntity;
import focandlol.reservation.entity.StoreEntity;
import focandlol.reservation.entity.auth.QManagerEntity;
import focandlol.reservation.type.ReservationType;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

import static focandlol.reservation.entity.QReservationEntity.*;
import static focandlol.reservation.entity.QStoreEntity.storeEntity;
import static focandlol.reservation.entity.auth.QManagerEntity.*;

@Repository
public class QueryReservationRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public QueryReservationRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public Page<ReservationEntity> findReservationForCustomer(Long customerId, ReservationSearchCond reservationSearchCond
            , Pageable pageable) {
        List<ReservationEntity> content = query
                .select(reservationEntity)
                .from(reservationEntity)
                .where(likeStoreName(reservationSearchCond.getStoreName()), eqCustomerId(customerId))
                .orderBy(getOrderSpecifier(reservationSearchCond))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query
                .select(reservationEntity.count())
                .from(reservationEntity)
                .where(likeStoreName(reservationSearchCond.getStoreName()), eqCustomerId(customerId))
                .fetchOne();

        return new PageImpl<>(content,pageable,count);
    }

    public Page<ReservationEntity> findReservationForManager(Long managerId, ReservationSearchCond reservationSearchCond
            , Pageable pageable) {
        List<ReservationEntity> content = query
                .select(reservationEntity)
                .from(reservationEntity)
                .join(reservationEntity.store, storeEntity).fetchJoin() // store를 fetch join
                .join(storeEntity.manager, managerEntity).fetchJoin() // manager를 fetch join
                .where(likeStoreName(reservationSearchCond.getStoreName())
                        , eqManagerId(managerId)
                        ,eqDate(reservationSearchCond.getDate())
                        ,eqReservationType(reservationSearchCond.getReservationType()))

                .orderBy(getOrderSpecifier(reservationSearchCond))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query
                .select(reservationEntity.count())
                .from(reservationEntity)
                .where(likeStoreName(reservationSearchCond.getStoreName())
                        , eqManagerId(managerId)
                        ,eqDate(reservationSearchCond.getDate())
                        ,eqReservationType(reservationSearchCond.getReservationType()))
                .fetchOne();

        return new PageImpl<>(content,pageable,count);
    }

    private BooleanExpression eqManagerId(Long managerId){
        if(managerId == null){
            return null;
        }
        return reservationEntity.store.manager.id.eq(managerId);
    }

    private BooleanExpression eqDate(LocalDate date){
        if(date == null){
            return null;
        }
        return reservationEntity.date.eq(date);
    }

    private BooleanExpression eqReservationType(ReservationType reservationType){
        if(reservationType == null){
            return null;
        }

        return reservationEntity.reservationType.eq(reservationType);
    }


    private OrderSpecifier<?> getOrderSpecifier(ReservationSearchCond reservationSearchCond){
        if(reservationSearchCond.getSortBy() != null &&
                reservationSearchCond.getSortBy().equalsIgnoreCase("storeName")){
            return reservationSearchCond.isAscending() ? reservationEntity.store.storeName.asc() : reservationEntity.store.storeName.desc();
        }
        return reservationEntity.id.asc();
    }

    private BooleanExpression eqCustomerId(Long customerId){
        if(customerId == null){
            return null;
        }
        return reservationEntity.customer.id.eq(customerId);
    }



    private BooleanExpression likeStoreName(String storeName) {
        if(StringUtils.hasText(storeName)){
            return reservationEntity.store.storeName.like("%"+storeName+"%");
        }
        return null;
    }


}
