# 기본 명령어
---
## 인덱스 생성
```
PUT /{인덱스명}
```

## 인덱스 조회
```
GET /{인덱스명}
```

## 인덱스 매핑 추가
```
PUT /{인덱스명}/_mappings
{
  "properties":{
     "name": {"type": "keyword"},
     "age": {"type": "integer"},
     "is_active": {"type": "boolean"}
  }
}
```

## 색인
```
POST /{인덱스명}/_doc
{
    "name": "kkm",
    "age": 24,
    "is_active": true
}
```

## 기본 조회
```
GET /{인덱스명}/_search
```
