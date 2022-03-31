package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDao {

	Connection conn = null;

	public MemberDao() {
		conn = DBConn.getConnection();
	}

	// userid로 아이디 검색
	public MemberVo getMember(String userid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberVo vo = null;

		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new MemberVo(rs.getString("USERID"), rs.getString("PASSWD"), rs.getString("USERNAME"),
						rs.getString("JOB"), rs.getString("GENDER"), rs.getString("INTRO"), rs.getString("INDATE"));
			}
			
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	// 모든 목록 조회
	public ArrayList<MemberVo> getMemberList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<MemberVo> voList = null;

		String sql = "SELECT * FROM MEMBER ORDER BY INDATE ASC";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (voList == null) {
					voList = new ArrayList<MemberVo>();
				}

				voList.add(new MemberVo(
						rs.getString("USERID"), rs.getString("PASSWD"),
						rs.getString("USERNAME"), rs.getString("JOB"), 
						rs.getString("GENDER"), rs.getString("INTRO"), 
						rs.getString("INDATE")));
			}
			
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return voList;
	}

	// 회원 추가
	public void insertMember(
			String userid, String passwd,
			String username, String job,
			String gender, String intro) {
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO MEMBER VALUES (?, ?, ?, ?, ?, ?, DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, passwd);
			pstmt.setString(3, username);
			pstmt.setString(4, job);
			pstmt.setString(5, gender);
			pstmt.setString(6, intro);
			
			pstmt.execute();
			System.out.println("1 행이 삽입 되었습니다.");
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 회원 삭제
	public void deleteMember(String userid) {
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			pstmt.execute();
			System.out.println(userid + "가(이) 삭제 되었습니다.");
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 회원 수정
	public void updateMember(String userid, String field, String setData) {
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MEMBER SET " + field + " = ? WHERE USERID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, setData);
			pstmt.setString(2, userid);
			
			pstmt.execute();
			System.out.printf("%s의 %s필드가 '%s'로 업데이트 되었습니다\n", userid, field, setData);
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
