package focandlol.reservation.reservation.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import focandlol.reservation.reservation.dto.ReservationSearchCond;
import focandlol.reservation.reservation.entity.ReservationEntity;
import focandlol.reservation.reservation.type.ReservationType;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static focandlol.reservation.manager.entity.QManagerEntity.managerEntity;
import static focandlol.reservation.reservation.entity.QReservationEntity.reservationEntity;
import static focandlol.reservation.store.entity.QStoreEntity.storeEntity;

@Repository
public class QueryReservationRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    /**
     *  EntityManager를 주입받아 JPAQueryFactory를 초기화
     */
    public QueryReservationRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    /**
     * 고객의 예약 목록 조회
     * @param customerId : 고객 ID
     * @param reservationSearchCond  : 예약 조회 조건
     * reservationSearchCond.storeName : 가게 이름으로 검색
     * reservationSearchCond.sortBy : 정렬 조건 (STORE_NAME, DATE, TIME)
     * reservationSearchCond.ascending : true면 오름차순, false면 내림차순
     * @param pageable : 페이징
     * @return 조건에 맞는 예약 목록과 페이징 정보를 포함한 Page 객체
     */
    public Page<ReservationEntity> findReservationForCustomer(Long customerId, ReservationSearchCond reservationSearchCond, Pageable pageable) {
        // 예약 목록 조회 쿼리
        List<ReservationEntity> content = query
                .select(reservationEntity) // 조회 대상 엔티티
                .from(reservationEntity) // 기준 테이블 지정
                .where(likeStoreName(reservationSearchCond.getStoreName()), eqCustomerId(customerId)) // 조건 적용
                .orderBy(getOrderSpecifier(reservationSearchCond)) // 정렬 조건 적용
                .offset(pageable.getOffset()) // 페이징 시작 위치 설정
                .limit(pageable.getPageSize()) // 페이징 크기 설정
                .fetch(); // 결과 리스트 가져오기

        // 총 데이터 수 조회
        Long count = query
                .select(reservationEntity.count()) // 전체 데이터 개수 조회
                .from(reservationEntity)
                .where(likeStoreName(reservationSearchCond.getStoreName()), eqCustomerId(customerId)) // 동일 조건 적용
                .fetchOne(); // 결과 하나 반환

        // Page 객체 생성 및 반환
        return new PageImpl<>(content, pageable, count);
    }

    /**
     * 매니저의 예약 목록 조회
     * @param managerId : 매니저 ID
     * @param reservationSearchCond : 예약 조회 조건
     * reservationSearchCond.storeName : 가게 이름으로 검색
     * reservationSearchCond.date : 특정 날짜로 필터링
     * reservationSearchCond.reservationType : 예약 타입으로 필터링 (APPROVED, UNAPPROVED, VISITED, CANCELED)
     * reservationSearchCond.sortBy : 정렬 조건 (STORE_NAME, DATE, TIME)
     * reservationSearchCond.ascending : true면 오름차순, false면 내림차순
     * @param pageable 페이징 정보
     * @return 조건에 맞는 예약 목록과 페이징 정보를 포함한 Page 객체
     */
    public Page<ReservationEntity> findReservationForManager(Long managerId, ReservationSearchCond reservationSearchCond, Pageable pageable) {
        // 예약 목록 조회 쿼리
        List<ReservationEntity> content = query
                .select(reservationEntity) // 조회 대상 엔티티
                .from(reservationEntity) // 기준 테이블 지정
                .join(reservationEntity.store, storeEntity).fetchJoin() // 가게 정보 fetch join
                .join(storeEntity.manager, managerEntity).fetchJoin() // 매니저 정보 fetch join
                .where(
                        likeStoreName(reservationSearchCond.getStoreName()), // 가게 이름 검색 조건
                        eqManagerId(managerId), // 매니저 ID 조건
                        eqDate(reservationSearchCond.getDate()), // 날짜 조건
                        eqReservationType(reservationSearchCond.getReservationType()) // 예약 타입 조건
                )
                .orderBy(getOrderSpecifier(reservationSearchCond)) // 정렬 조건 적용
                .offset(pageable.getOffset()) // 페이징 시작 위치 설정
                .limit(pageable.getPageSize()) // 페이징 크기 설정
                .fetch(); // 결과 리스트 가져오기

        // 총 데이터 수 조회
        Long count = query
                .select(reservationEntity.count()) // 전체 데이터 개수 조회
                .from(reservationEntity)
                .where(
                        likeStoreName(reservationSearchCond.getStoreName()), // 가게 이름 검색 조건
                        eqManagerId(managerId), // 매니저 ID 조건
                        eqDate(reservationSearchCond.getDate()), // 날짜 조건
                        eqReservationType(reservationSearchCond.getReservationType()) // 예약 타입 조건
                )
                .fetchOne(); // 결과 하나 반환

        // Page 객체 생성 및 반환
        return new PageImpl<>(content, pageable, count);
    }

    /**
     * 매니저 ID 조건 생성
     * 검색어가 있는 경우 조건을 생성, 없는 경우 null 반환
     * 반환값이 null일 경우 where 문에서 자동으로 무시
     * @param managerId 매니저 ID
     * @return 매니저 ID에 대한 BooleanExpression 조건 반환
     */
    private BooleanExpression eqManagerId(Long managerId) {
        if (managerId == null) {
            return null; // 조건 없음
        }
        return reservationEntity.store.manager.id.eq(managerId);
    }

    /**
     * 특정 날짜 조건 생성
     * 검색어가 있는 경우 조건을 생성, 없는 경우 null 반환
     * 반환값이 null일 경우 where 문에서 자동으로 무시
     * @param date 필터링할 날짜
     * @return 날짜에 대한 BooleanExpression 조건 반환
     */
    private BooleanExpression eqDate(LocalDate date) {
        if (date == null) {
            return null; // 조건 생략
        }
        return reservationEntity.date.eq(date);
    }

    /**
     * 예약 타입 조건 생성
     * 검색어가 있는 경우 조건을 생성, 없는 경우 null 반환
     * 반환값이 null일 경우 where 문에서 자동으로 무시
     * @param reservationType 예약 타입
     * @return 예약 타입에 대한 BooleanExpression 조건 반환
     */
    private BooleanExpression eqReservationType(ReservationType reservationType) {
        if (reservationType == null) {
            return null; // 조건 생략
        }
        return reservationEntity.reservationType.eq(reservationType);
    }

    /**
     * 정렬 조건에 따라 OrderSpecifier 객체를 반환
     * 정렬 기준은 StoreSearchCond의 sortBy와 ascending 여부에 따라 바뀜
     * 기본값은 id 오름차순 정렬
     * @param reservationSearchCond 예약 조회 조건
     * reservationSearchCond.sortBy: 정렬 기준 (STORE_NAME, DATE, TIME)
     * reservationSearchCond.ascending: true면 오름차순, false면 내림차순
     * @return OrderSpecifier 객체로 정렬 조건 정의
     */
    private OrderSpecifier<?> getOrderSpecifier(ReservationSearchCond reservationSearchCond) {
        return Optional.ofNullable(reservationSearchCond.getSortBy())
                .map(sortBy -> switch (sortBy) {
                    //가게 이름으로 정렬 (오름차순/내림차순 선택)
                    case STORE_NAME -> reservationSearchCond.isAscending() ? reservationEntity.store.storeName.asc() : reservationEntity.store.storeName.desc();
                    //날짜로 정렬 (오름차순/내림차순 선택)
                    case DATE -> reservationSearchCond.isAscending() ? reservationEntity.date.asc() : reservationEntity.date.desc();
                    //시간으로 정렬 (오름차순/내림차순 선택)
                    case TIME -> reservationSearchCond.isAscending() ? reservationEntity.time.asc() : reservationEntity.time.desc();
                })
                .orElse(reservationEntity.id.asc()); // 기본 정렬 조건(id 오름차순)
    }

    /**
     * 고객 ID 조건 생성
     * 검색어가 있는 경우 조건을 생성, 없는 경우 null 반환
     * 반환값이 null일 경우 where 문에서 자동으로 무시
     * @param customerId 고객 ID
     * @return 고객 ID에 대한 BooleanExpression 조건 반환
     */
    private BooleanExpression eqCustomerId(Long customerId) {
        if (customerId == null) {
            return null; // 조건 생략
        }
        return reservationEntity.customer.id.eq(customerId);
    }

    /**
     * 가게 이름 검색 조건 생성
     * 검색어가 있는 경우 `like` 조건을 생성, 없는 경우 null 반환
     * 반환값이 null일 경우 where 문에서 자동으로 무시
     * @param storeName 검색할 가게 이름
     * @return 가게 이름에 대한 BooleanExpression 조건 반환 (부분 검색 적용)
     */
    private BooleanExpression likeStoreName(String storeName) {
        if (StringUtils.hasText(storeName)) {
            return reservationEntity.store.storeName.like("%" + storeName + "%");
        }
        return null; // 조건 생략
    }


}
