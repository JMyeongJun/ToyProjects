package view;

import model.MemberDao;
import model.MemberVo;

public class TestMember {
	
	public static void main(String[] args) {
	
		MemberDao dao = new MemberDao();
		
		// 개인조회 : USERID로 검색
//		String userid = "SKY"; // 검색 입력
//		MemberVo vo = dao.getMember(userid);
//		if(vo != null) {
//			System.out.println(vo);
//		}else {
//			System.out.printf("'%s' 검색 결과 없음!!", userid);
//		}
		
		// 목록 조회
		for(MemberVo vo2 : dao.getMemberList()) {
			System.out.println(vo2);
		}
		
		// 회원 추가
		dao.insertMember("SKY2", "1234", "관리자", "회사원", "여", "관리자2입니다.");
		
		// 회원 삭제
//		dao.deleteMember("SKY2");
		
		// 회원 수정
//		dao.updateMember("SKY2", "USERNAME", "관리자2");
		
//		dao.close();
	}
	
}
