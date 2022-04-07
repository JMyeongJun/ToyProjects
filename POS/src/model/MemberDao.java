package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MemberDao {
	Connection conn = null;
	
	public MemberDao() {
		conn = DBConn.getConnection();
	}
	
	public Vector<MemberVo> getMemberList(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM MEMBERS";
		
		Vector<MemberVo> voList = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			voList = new Vector<MemberVo>();
			
			while(rs.next()) {
				MemberVo vo = new MemberVo(
						rs.getString("MEMBER_NAME"), rs.getString("PHONE_NUMBER"),
						rs.getInt("POINT"));
				voList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return voList;
	}
	
	public void insertMember(String memberName, String phoneNumber) {
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO MEMBERS VALUES "
				+ "(?, ?, 0)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberName);
			pstmt.setString(2, phoneNumber);
			
			pstmt.execute();
			System.out.println("MemberDao: 1 행이 삽입 되었습니다.");
			
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateMember(String currentPhoneNumber, String field, String setData) {
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBERS SET " + field + " = ? WHERE PHONE_NUMBER = ?";

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, setData);
			pstmt.setString(2, currentPhoneNumber);

			pstmt.executeUpdate();
			System.out.printf("MemberDao: %s의 %s필드가 '%s'으로 업데이트 되었습니다\n", currentPhoneNumber, field, setData);

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMember(String phoneNumber) {
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM MEMBERS WHERE PHONE_NUMBER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneNumber);

			pstmt.execute();
			System.out.println("MemberDao: '" + phoneNumber + "'가(이) 삭제 되었습니다.");

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
