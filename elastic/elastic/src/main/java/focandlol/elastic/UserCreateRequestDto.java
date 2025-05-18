package focandlol.elastic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequestDto {
  private String id;

  private String name;

  private Long age;

  private Boolean isActive;

  public UserDocument to(){
    return UserDocument.builder()
        .id(id)
        .age(age)
        .name(name)
        .isActive(isActive)
        .build();
  }
}
