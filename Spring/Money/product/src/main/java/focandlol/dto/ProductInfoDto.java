package focandlol.dto;

import focandlol.converter.code.OrganizationCode;
import focandlol.converter.code.ProductCode;
import focandlol.domain.ProductInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class ProductInfoDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {

        @Schema(description = "Product Code", example = "001")
        private String organizationCode;
        @Schema(description = "Product Code", example = "001")
        private String productCode;
        @Schema(description = "Product Code", example = "001")
        private Double productMaximumInterest;
        @Schema(description = "Product Code", example = "001")
        private Double productMinimumInterest;
        @Schema(description = "Product Code", example = "001")
        private String productName;

        public ProductInfo toEntity() {
            return ProductInfo.builder()
                    .organizationCode(OrganizationCode.findOrganization(this.organizationCode))
                    .productCode(ProductCode.findProduct(this.productCode))
                    .productMaximumInterest(this.productMaximumInterest)
                    .productMinimumInterest(this.productMinimumInterest)
                    .productName(this.productName)
                    .build();
        }
    }

}
