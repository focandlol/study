package focandlol.relastic.domain;

import focandlol.relastic.dto.CreateProductRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  private int price;

  private double rating;

  private String category;

  public static Product fromCreateProductRequestDto(CreateProductRequestDto createProductRequestDto) {
    return Product.builder()
        .name(createProductRequestDto.getName())
        .description(createProductRequestDto.getDescription())
        .price(createProductRequestDto.getPrice())
        .rating(createProductRequestDto.getRating())
        .category(createProductRequestDto.getCategory())
        .build();
  }
}
