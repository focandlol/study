package focandlol.reservation.repository.store;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import focandlol.reservation.dto.store.StoreSearchCond;
import focandlol.reservation.entity.QStoreEntity;
import focandlol.reservation.entity.StoreEntity;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static focandlol.reservation.entity.QStoreEntity.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Repository
public class QueryStoreRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public QueryStoreRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public Page<StoreEntity> findStores(StoreSearchCond storeSearchCond, String storeName, Pageable pageable) {
        List<StoreEntity> content = query
                .select(storeEntity)
                .from(storeEntity)
                .where(likeStoreName(storeName))
                .orderBy(getOrderSpecifier(storeSearchCond))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query
                .select(storeEntity.count())
                .from(storeEntity)
                .where(likeStoreName(storeName))
                .fetchOne();

        return new PageImpl<>(content,pageable,count);
    }

    private OrderSpecifier<?> getOrderSpecifier(StoreSearchCond storeSearchCond){
        if(storeSearchCond.getSortBy().equalsIgnoreCase("storeName")){
            return storeSearchCond.isAscending() ? storeEntity.storeName.asc() : storeEntity.storeName.desc();
        }
        return storeEntity.id.asc();
    }

    private BooleanExpression likeStoreName(String storeName) {
        if(StringUtils.hasText(storeName)){
            return storeEntity.storeName.like("%"+storeName+"%");
        }
        return null;
    }
}
