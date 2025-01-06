package focandlol.resproject.store.dto;

import focandlol.resproject.store.type.StoreSortType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 가게 검색 조건
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreSearchCond {
    /**
     * 가게 이름으로 검색
     */
    private String storeName;

    /**
     * 가게 정렬 조건
     */
    private StoreSortType sortBy;

    /**
     * 가게 내림차순, 오름차순 조건
     */
    private boolean ascending;
}
