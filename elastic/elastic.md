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
## 기본 조회
```
GET /{인덱스명}/_search
```
