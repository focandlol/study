package focandlol.reservation.dto.store;

import focandlol.reservation.entity.StoreEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class UpdateStoreDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long managerId;
        @NotBlank
        private String storeName;

        private String storePhoneNumber;

        @NotBlank
        private String location;

        private boolean cesco;

        @NotNull
        private Integer totalSeat;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private Long storeId;

        private Long managerId;

        private String storeName;

        private String storePhoneNumber;

        private String location;

        private boolean cesco;

        private Integer totalSeat;

        public static Response from(StoreEntity store){
            return Response.builder()
                    .storeId(store.getId())
                    .managerId(store.getManager().getId())
                    .storeName(store.getStoreName())
                    .storePhoneNumber(store.getStorePhoneNumber())
                    .location(store.getLocation())
                    .cesco(store.isCesco())
                    .totalSeat(store.getTotalSeat())
                    .build();
        }
    }
}
