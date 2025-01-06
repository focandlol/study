package focandlol.resproject.store.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import focandlol.resproject.store.dto.StoreSearchCond;
import focandlol.resproject.store.entity.StoreEntity;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static focandlol.resproject.store.entity.QStoreEntity.storeEntity;


@Repository
public class QueryStoreRepository {

    private final JPAQueryFactory query;

    /**
     * EntityManager를 주입받아 JPAQueryFactory를 초기화
     */
    public QueryStoreRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    /**
     * 가게 목록
     *
     * @param storeSearchCond : 가게 조회 조건
     *                        storeSearchCond.storeName : 가게 이름으로 검색
     *                        storeSearchCond.sortBy : 정렬 조건
     *                        STORE_NAME : 가게 이름으로 정렬
     *                        STAR : 가게 별점으로 정렬
     *                        storeSearchCond.ascending : 내림차순 or 오름차순
     * @param pageable        : 페이징
     * @return : 검색 조건에 맞는 가게 목록과 페이징 정보를 포함한 Page 객체
     */
    public Page<StoreEntity> findStores(StoreSearchCond storeSearchCond, Pageable pageable) {
        // 가게 목록 조회 쿼리
        List<StoreEntity> content = query
                .select(storeEntity) // 조회 대상 엔티티
                .from(storeEntity) // 가게 엔티티를 기준으로 조회
                .where(likeStoreName(storeSearchCond.getStoreName())) // 가게 이름 조건 적용
                .orderBy(getOrderSpecifier(storeSearchCond)) // 정렬 조건 적용
                .offset(pageable.getOffset()) // 페이징: 시작 위치
                .limit(pageable.getPageSize()) // 페이징: 페이지 크기
                .fetch(); // 결과 리스트 가져오기

        // 총 데이터 수 조회 쿼리 (페이징 처리를 위해 필요)
        Long count = query
                .select(storeEntity.count()) // 가게 엔티티의 총 개수
                .from(storeEntity)
                .where(likeStoreName(storeSearchCond.getStoreName())) // 동일한 조건 적용
                .fetchOne(); // 결과 하나만 반환

        // Page 객체 생성 및 반환
        return new PageImpl<>(content, pageable, count);
    }

    /**
     * 정렬 조건에 따라 OrderSpecifier 객체를 반환
     * 정렬 기준은 StoreSearchCond의 sortBy와 ascending 여부에 따라 바뀜
     * 기본값은 id 오름차순 정렬
     *
     * @param storeSearchCond : 가게 조회 조건
     *                        storeSearchCond.sortBy : 정렬 조건
     *                        STORE_NAME : 가게 이름으로 정렬
     *                        STAR : 가게 별점으로 정렬
     *                        storeSearchCond.ascending : 내림차순 or 오름차순
     * @return OrderSpecifier 객체로 정렬 조건을 정의
     */
    private OrderSpecifier<?> getOrderSpecifier(StoreSearchCond storeSearchCond) {
        // Optional과 switch 표현식을 사용하여 정렬 조건 처리
        return Optional.ofNullable(storeSearchCond.getSortBy())
                .map(sortBy -> switch (sortBy) {
                    // 가게 이름으로 정렬 (오름차순/내림차순 선택)
                    case STORE_NAME ->
                            storeSearchCond.isAscending() ? storeEntity.storeName.asc() : storeEntity.storeName.desc();
                    // 평점으로 정렬 (오름차순/내림차순 선택)
                    case STAR -> storeSearchCond.isAscending() ? storeEntity.star.asc() : storeEntity.star.desc();
                    default -> storeEntity.id.asc();
                })
                // 정렬 조건이 없을 경우 기본값(id 오름차순)을 반환
                .orElse(storeEntity.id.asc());
    }

    /**
     * 가게 이름 검색 조건을 반환.
     * 검색어가 있는 경우 `like` 조건을 생성, 없는 경우 null 반환
     * 반환값이 null일 경우 where 문에서 자동으로 무시
     *
     * @param storeName 검색할 가게 이름 (부분 검색 가능).
     * @return BooleanExpression 객체로, 조건을 정의
     */
    private BooleanExpression likeStoreName(String storeName) {
        // 검색어가 있는 경우 '%검색어%' 형식으로 부분 일치 조건 생성
        if (StringUtils.hasText(storeName)) {
            return storeEntity.storeName.like("%" + storeName + "%");
        }
        // 검색어가 없으면 조건 생략(null 반환)
        return null;
    }
}
