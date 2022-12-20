# SWManagement
Agile하게 개발일정을 관리하기 위한 툴 백엔드입니다.
---
### Stack
- AWS ec2, Docker, Jenkins
- Spring boot, Java, Jpa, QueryDsl

--- 
### 해결한 문제
- QueryDsl을 사용시 DTO로 데이터를 받아와 N+1문제를 해결하였습니다.
- ZAZY로 받아오던 데이터를 필요시에는 더티채킹이 아니라 join으로 가져와서 한방쿼리를 날려 성능향상이 있었습니다.
