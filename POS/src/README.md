# model(dao, vo)
- DaoTest.java 로 dao 테스트 가능
# sql(테이블 생성 sql 구문)
- 이걸로 먼저 oracle에 테이블 생성
# view(화면 구현)
- ***BasicPanel 변경 X***
- 각 메인 Panel들은 BasicPanel extends 후 사용
- BasicPanel상속된 패널 테스트 시 MainFrmae.java 사용 (OrderPanel 초기화 부분 각 Panel명으로 변경)
