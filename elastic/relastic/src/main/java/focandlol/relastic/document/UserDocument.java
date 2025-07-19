package focandlol.relastic.document;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Document(indexName = "users")
public class UserDocument {
  @Id
  private String id;

  @Field(type = FieldType.Keyword)
  private String name;

  @Field(type = FieldType.Long)
  private Long age;

  @Field(type = FieldType.Boolean)
  private Boolean isActive;
}
