package focandlol.elastic.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "products")
@Setting(settingPath = "/elasticsearch/products-settings.json")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProductDocument {

  @Id
  private String id;

  @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "products_name_analyzer"),
    otherFields = {
      @InnerField(suffix = "auto_complete", type = FieldType.Search_As_You_Type, analyzer = "nori")
    }
  )
  private String name;

  @Field(type = FieldType.Text, analyzer = "products_description_analyzer")
  private String description;

  @Field(type = FieldType.Integer)
  private Integer price;

  @Field(type = FieldType.Long)
  private Double rating;

  @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "products_category_analyzer"),
      otherFields = {
        @InnerField(suffix = "raw", type = FieldType.Keyword)
      }
  )
  private String category;

  public static ProductDocument fromEntity(Product product) {
    return ProductDocument.builder()
        .id(product.getId().toString())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .rating(product.getRating())
        .category(product.getCategory())
        .build();
  }
}
