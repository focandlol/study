# 기본 명령어
---
## 인덱스 생성
```
PUT /{인덱스명}
```

## 인덱스 삭제
```
DELETE /{인덱스}
```

## 인덱스 정보 조회
```
GET /{인덱스명}
```

## 인덱스 매핑 추가
```
PUT /{인덱스명}/_mapping
{
  "properties":{
     "name": {"type": "keyword"},
     "age": {"type": "integer"},
     "is_active": {"type": "boolean"}
  }
}
```

## 기본 색인 (_id 랜덤)
```
POST /{인덱스명}/_doc
{
    "name": "kkm",
    "age": 24,
    "is_active": true
}
```

## 색인 (_id 지정)
- 같은 id 있을 경우 예외
```
POST /{인덱스명}/_create/{_id 값}
{
    "name": "ko",
    "age": 20,
    "is_active": false
}
```

## 색인 (upsert, _id 지정)
```
PUT /{인덱스명}/_doc/{_id 값}
{
    "name": "koko",
    "age": 20,
    "is_active": false
}
```

## 특정 필드만 update
```
POST /{인덱스명}/_update/{_id 값}
{
  "doc": {
      "age": 33,
      "name": "kokokoko"
  }
}
```

## 삭제
```
DELETE /{인덱스명}/_doc/{_id 값}
```
## 기본 검색
```
GET /{인덱스명}/_search
```

## 조건 검색 예시
```
get /products/_search
{
    "query":{
        "match": {
          "name": "서울특별시 ㅁㅁ구 ㅁㅁ동"
        }
    }
}
```

---

# spring data elasticsearch

## 인덱스 정의
```
@Document(indexName = "users")
@AllArgsConstructor
@Getter
@Setter
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
```
- 만약 elasticsaerch에 정의한 인덱스가 존재하지 않는다면 스프링 부트 실행 시 정의한 대로 인덱스 자동 생성

## 기본 CRUD
### CREATE
```
userDocumentRepository.save(request.to());
```
### READ ALL
```
userDocumentRepository.findAll(PageRequest.of(0,10));
```
### READ ONE
```
userDocumentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 문서"));
```
### UPDATE
```
UserDocument exist = userDocumentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 문서"));

    exist.setAge(request.getAge());
    exist.setName(request.getName());
    exist.setIsActive(request.getIsActive());

    return userDocumentRepository.save(exist);
```
### DELETE
```
UserDocument exist = userDocumentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 문서"));

    userDocumentRepository.delete(exist);
```
