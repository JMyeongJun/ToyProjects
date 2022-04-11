package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class CategoryDao {
	Connection conn = null;

	public CategoryDao() {
		conn = DBConn.getConnection();
	}

	// 아이디로 카테고리 조회
	public CategoryVo getCategoryById(int categoryId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM CATEGORIES WHERE CATEGORY_ID = ?";

		CategoryVo vo = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);

			rs = pstmt.executeQuery();
			
			vo = null;

			if (rs.next()) {
				vo = new CategoryVo(Integer.parseInt(rs.getString("CATEGORY_ID")),
						rs.getString("CATEGORY_NAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}
	
	// 이름으로 카테고리 조회
	public CategoryVo getCategoryByName(String categoryName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM CATEGORIES WHERE CATEGORY_NAME = ?";

		CategoryVo vo = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);

			rs = pstmt.executeQuery();
			
			vo = null;

			if (rs.next()) {
				vo = new CategoryVo(Integer.parseInt(rs.getString("CATEGORY_ID")),
						rs.getString("CATEGORY_NAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	// 모든 카테고리 조회
	public Vector<CategoryVo> getCategoryList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM CATEGORIES ORDER BY CATEGORY_ID ASC";

		Vector<CategoryVo> voList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			voList = new Vector<CategoryVo>();

			while (rs.next()) {
				CategoryVo vo = new CategoryVo(Integer.parseInt(rs.getString("CATEGORY_ID")),
						rs.getString("CATEGORY_NAME"));
				voList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return voList;
	}

	// 카테고리 추가
	public void insertCategory(String categoryName) {
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO CATEGORIES VALUES" + "((SELECT NVL(MAX(CATEGORY_ID), 0) + 1 FROM CATEGORIES), " + "?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);

			pstmt.execute();
			System.out.println("CategoryDao: 1 행이 삽입 되었습니다.");

			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 카테고리 수정
	public void updateCategory(String currentCategoryName, String categoryName) {
		PreparedStatement pstmt = null;

		String sql = "UPDATE CATEGORIES SET CATEGORY_NAME = ?" + "WHERE CATEGORY_NAME = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);
			pstmt.setString(2, currentCategoryName);

			if (pstmt.executeUpdate() > 0) {
				System.out
						.println("CategoryDao: '" + currentCategoryName + "' -> '" + categoryName + "' 로 이름 변경되었습니다.");
			} else {
				System.out.println("CategoryDao: 수정 실패하였습니다.");
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 카테고리 삭제
	public void deleteCategory(String categoryName) {
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM CATEGORIES " + "WHERE CATEGORY_NAME = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);

			pstmt.execute();
			System.out.println("CategoryDao: '" + categoryName + "' 가(이) 삭제 되었습니다.");

			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
