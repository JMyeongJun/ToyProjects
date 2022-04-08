# model(dao, vo)
- DaoTest.java 로 dao 테스트 가능
#### 데이터생성순서
카테고리 -> 메뉴 -> 멤버 -> 주문
# sql(테이블 생성 sql 구문)
## Oracle 설정(dao사용준비)
- 계정 만들기 POS/1234
- 계정 권한 설정
- 계정 tablespace 설정
- POS_table_create.sql 이용해서 oracle 테이블 생성
# view(화면 구현)
- ***BasicPanel 변경 X***
- 각 메인 Panel들은 BasicPanel extends 후 사용
- BasicPanel상속된 패널 테스트 시 MainFrmae.java 사용 (OrderPanel 초기화 부분 각 Panel명으로 변경)
