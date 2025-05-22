package focandlol.elastic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestDto {
  private String name;
  private String description;
  private int price;
  private double rating;
  private String category;
}
