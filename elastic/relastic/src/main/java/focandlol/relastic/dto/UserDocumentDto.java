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
public class UserDocumentDto {

  private String id;
  private String name;
  private Long age;
  private Boolean isActive;

  public static UserDocumentDto from(UserDocument document){
    return UserDocumentDto.builder()
        .id(document.getId())
        .name(document.getName())
        .age(document.getAge())
        .isActive(document.getIsActive())
        .build();
  }

}
