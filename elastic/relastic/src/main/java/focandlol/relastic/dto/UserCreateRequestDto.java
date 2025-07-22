package focandlol.relastic.dto;

import focandlol.relastic.domain.UserDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequestDto {

  private String id;
  private String name;
  private Long age;
  private Boolean isActive;

  public UserDocument toUserDocument() {
    return UserDocument.builder()
        .id(id)
        .name(name)
        .age(age)
        .isActive(isActive)
        .build();
  }

}
