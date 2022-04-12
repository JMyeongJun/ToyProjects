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

	public Vector<MemberVo> getMemberList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM MEMBERS";

		Vector<MemberVo> voList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			voList = new Vector<MemberVo>();

			while (rs.next()) {
				MemberVo vo = new MemberVo(rs.getString("MEMBER_NAME"), rs.getString("PHONE_NUMBER"),
						rs.getInt("POINT"));
				voList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return voList;
	}

	public MemberVo getMember(String phoneNumber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM MEMBERS WHERE PHONE_NUMBER = ?";

		MemberVo vo = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneNumber);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new MemberVo(rs.getString("MEMBER_NAME"), rs.getString("PHONE_NUMBER"), rs.getInt("POINT"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	public void insertMember(String memberName, String phoneNumber) {
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO MEMBERS VALUES " + "(?, ?, 0)";

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

	public void updateMember(String currentPhoneNumber, String memberName, String phoneNumber) {
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBERS SET MEMBER_NAME = ?, PHONE_NUMBER = ? WHERE PHONE_NUMBER = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberName);
			pstmt.setString(2, phoneNumber);
			pstmt.setString(3, currentPhoneNumber);

			pstmt.executeUpdate();
			System.out.println("MemberDao: 수정 되었습니다\n");

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
