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

## 인덱스 + 분석기 + 매핑 생성
```
PUT /{인덱스명}
{
  "settings": {
    "analysis": {
      "filter": {
        "custom_synonym_filter": {
          "type": "synonym",
          "synonyms": ["seoul, 서울울"]
        }
      },
      "analyzer": {
        "custom_analyzer": {
          "char_filter": [],
          "tokenizer": "standard",
          "filter": ["lowercase", "custom_synonym_filter"]
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "name": {
        "type": "text",
        "analyzer": "custom_analyzer"
      },
      "price": {
        "type": "integer"
      },
      "in_stock": {
        "type": "boolean"
      }
    }
  }
}
```
## multi-field mapping
```
PUT /addresses
{
  "mappings": {
    "properties": {
      "city": {
        "type": "text",
        "analyzer": "nori"
      },
      "address": {
        "type": "text",
        "analyzer": "nori",
        "fields":{
          "raw":{
            "type":"keyword"
          }
        }
      }
    }
  }
}
```
## search_as_you_type mapping
```
PUT /addresses
{
  "mappings": {
    "properties": {
      "address": {
        "type": "search_as_you_type",
        "analyzer": "nori"
      }
    }
  }
}
```
## search_as_you_type 조회
```
get /addresses/_search
{
  "query": {
    "multi_match": {
      "query": "서울특별시",
      "fields": ["address","address._2gram","address._3gram"]
    }
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

## 분석기 테스트
```
GET /_analyze
{
  "text": "",
  "tokenizer": "standard",
  "filter": ["lowercase"]
}
```

## 특정 인덱스 필드 분석기 테스트
```
GET /{인덱스명}/_analyze
{
  "field": "name",
  "text": "서울특별시 ㅁㅁ구 ㅁㅁ동"
}
```

## 다중 조건 쿼리 예시 bool 쿼리 and

```json
GET /addresses/_search
{
  "query": {
    "bool": {
      "must_not": [
        {
          "term":{
            "category": "도시"
          }
        }
      ],
      "must": [
        {
          "match": {
            "full_address": "서울특별시"
          }
        }
      ],
      "filter": [
        {
          "term": {
            "is_public_data": false
          }
        }
      ],
      "should": [
        {
          "range":{
             "rating":{
                "gte":4.5
              }
           }
        }
      ]
    }
  }
}
```

---

##  term 쿼리 예시

```json
GET /addresses/_search
{
  "query": {
    "term": {
      "region_code": "1111000000"
    }
  }
}
```

---

## terms = IN 쿼리 예시

```json
GET /addresses/_search
{
  "query": {
    "terms": {
      "district": ["종로구", "중구", "강남구"]
    }
  }
}
```

## range 쿼리
```
get /addresses/_search
{
  "query": {
    "bool": {
      "filter": [
        {
          "range": {
            "size": {
              "gte": 30
            }
          }
        },
        {
          "range": {
            "created_at": {
              "gte": "2025-01-01"
            }
          }
        }
      ]
    }
  }
}
```
## fuzziness 예시
```
get /addresses/_search
{
    "query":{
        "match":{
            "address":{
                "query":"서울별특시",
                "fuzziness":"auto"
            }
        }
    }
}
```
## multi_match 예시
```
get /addresses/_search
{
    "query":{
        "multi_match": {
          "query": "서울특별시",
          "fields": ["city^2","adderss"]
        }
    }
}
```

## highlighting 예시
```
get /addresses/_search
{
    "query":{
        "multi_match": {
          "query": "서울특별시",
          "fields": ["city^2","adderss"]
        }
    },
    "highlight": {
        "fields": {
            "city":{
                "pre_tags": ["<mark>"],
                "post_tags":["</mark>"]
            },
            "adderss":{
                "pre_tags":["<b>"],
                "post_tags":["</b>"]

            }
        }
    }
}
```
## paging, sort
```
get /addersses/_search
{
    "query":{
        "match": {
          "address": "서울특별시"
        }
    },
    "size":3,
    "from":0,
    "sort": [
      {
        "id": {
          "order": "desc"
        }
      }
    ]
}
```


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

---

# 정리
## 역인덱스(Inverted Index)란

**역인덱스(Inverted Index)**는 각 단어(토큰)가 어떤 문서에 포함되어 있는지 기록한 구조입니다.  
검색 시 단어가 들어간 문서를 빠르게 찾을 수 있습니다.

예시

- 문서1: 서울 특별시 강남구
- 문서2: 서울 강북구
- 문서3: 부산 해운대구

| 토큰    | 문서 id  |
| ------- | -------- |
| 서울     | 1, 2     |
| 특별시   | 1        |
| 강남구   | 1        |
| 강북구   | 2        |
| 부산     | 3        |
| 해운대구 | 3        |

- "서울"로 검색하면 [1, 2]번 문서가 바로 조회됨
- 여러 단어로 검색하면, 일치 단어 수/빈도/희귀성 등을 반영해 **score**(점수) 계산  
- **text** 타입 필드에 적용됨

---

## 애널라이저(Analyzer)란

**애널라이저(Analyzer)**는 텍스트를 검색에 쓸 토큰(단어)으로 변환하는 모듈  
색인/검색 모두 이 과정을 거침

애널라이저 동작 순서

1. **캐릭터 필터 (character filter)**
   - 텍스트를 자르기 전 미리 가공
   - 예시: `<h1>서울</h1>` → `서울` (HTML 제거)
   - 예시: `010-1234-5678` → `010 1234 5678` (특수문자 치환)

2. **토크나이저 (tokenizer)**
   - 텍스트를 토큰(단어) 단위로 분리
   - 예시: "서울 강남구" → [서울, 강남구]

3. **토큰 필터 (token filter)**
   - 잘린 토큰을 추가로 가공
   - 예시: 모두 소문자로 변경 (`SEOUL` → `seoul`)
   - 예시: 불용어 제거 (`[the, seoul, station]` → `[seoul, station]`)
   - 예시: 어근 변환 (`jumped` → `jump`)

**애널라이저는 텍스트를 토큰 배열로 변환하는 핵심 모듈**  
정의에 따라 같은 텍스트도 다르게 분리/검색됨

---

> **정리**
> - 역인덱스: 단어-문서ID 매핑 구조
> - 애널라이저: 텍스트를 토큰으로 변환하는 모듈

## 매핑(Mapping)이란?

매핑은 Elasticsearch에서 각 필드가 어떤 데이터 타입을 가지는지 정의하는 구조 
관계형 DB의 스키마와 유사한 개념

예시:

```json
{
  "mappings": {
    "properties": {
      "title":   { "type": "text" },
      "price":   { "type": "integer" },
      "created": { "type": "date" }
    }
  }
}
```

---

## 데이터 타입(Data Type)

자주 사용하는 데이터 타입:

### 숫자

| 타입     | 설명           |
|----------|----------------|
| `integer`| 10억 이하 정수 |
| `long`   | 10억 이상 정수 |
| `double` | 소수 포함 실수 |

### 문자열

| 타입      | 설명 |
|-----------|------|
| `text`    | 텍스트를 분석(토큰화)하여 저장 → 유사 검색용 |
| `keyword` | 분석 없이 원문 그대로 저장 → 정확 일치 검색용 (예: 이메일, 휴대폰 번호 등) |

### 기타

| 타입      | 설명         |
|-----------|--------------|
| `date`    | 날짜/시간 값 |
| `boolean` | true/false   |

---

## 매핑의 특성

### null 허용

- 필드가 존재하지 않아도, 또는 null이어도 인덱싱 가능
- 예시:

```json
{ "title": null, "content": "내용" }
{ "content": "내용만 있음" }
```

###  배열 허용

- `array` 타입이 따로 없고, 하나의 필드에 여러 값 넣으면 자동으로 배열처럼 처리
- 예시:

```json
{ "tags": "사과" }
{ "tags": ["사과", "바나나"] }
```

→ `"사과"`로 검색하면 두 문서 모두 검색

---

> **정리**
> - 매핑: 필드의 데이터 타입 구조 정의
> - `text`: 분석된 문자열 (유사 검색용)
> - `keyword`: 분석 안 된 문자열 (정확 매칭용)
> - null/배열 허용: 유연한 스키마 구조

