package focandlol.reservation.store.dto;

import focandlol.reservation.store.entity.StoreEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 가게 수정
 */
public class UpdateStoreDto {

    /**
     * 가게 수정 요청
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{

        @NotBlank
        private String storeName;

        private String storePhoneNumber;

        @NotBlank
        private String description;

        @NotBlank
        private String location;

        private boolean cesco;

        @NotNull
        private Integer totalSeat;
    }

    /**
     * 가게 수정 응답
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{

        private String storeName;

        private String storePhoneNumber;

        private String location;

        private String description;

        private boolean cesco;

        private Integer totalSeat;

        public static Response from(StoreEntity store){
            return Response.builder()
                    .storeName(store.getStoreName())
                    .storePhoneNumber(store.getStorePhoneNumber())
                    .location(store.getLocation())
                    .description(store.getDescription())
                    .cesco(store.isCesco())
                    .totalSeat(store.getTotalSeat())
                    .build();
        }
    }
}
