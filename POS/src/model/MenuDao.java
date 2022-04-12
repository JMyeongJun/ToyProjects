package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class MenuDao {
	Connection conn = null;
	
	public MenuDao() {
		conn = DBConn.getConnection();
	}
	
	// id로 메뉴 정보 가져오기
	public MenuVo getMenuById(int menuId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM MENU WHERE MENU_ID = ?";

		MenuVo vo = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menuId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new MenuVo(Integer.parseInt(rs.getString("MENU_ID")),
						rs.getString("MENU_NAME"),
						Integer.parseInt(rs.getString("PRICE")),
						Integer.parseInt(rs.getString("CATEGORY_ID")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}
	
	// 이름으로 메뉴 정보 가져오기
		public MenuVo getMenuByName(String menuName) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String sql = "SELECT * FROM MENU WHERE MENU_NAME = ?";

			MenuVo vo = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, menuName);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					vo = new MenuVo(Integer.parseInt(rs.getString("MENU_ID")),
							rs.getString("MENU_NAME"),
							Integer.parseInt(rs.getString("PRICE")),
							Integer.parseInt(rs.getString("CATEGORY_ID")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return vo;
		}
	
	// 전체 메뉴 리스트 가져오기
	public Vector<MenuVo> getMenuList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM MENU ORDER BY MENU_ID ASC";

		Vector<MenuVo> voList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			voList = new Vector<MenuVo>();

			while (rs.next()) {
				MenuVo vo = new MenuVo(Integer.parseInt(rs.getString("MENU_ID")),
						rs.getString("MENU_NAME"),
						Integer.parseInt(rs.getString("PRICE")),
						Integer.parseInt(rs.getString("CATEGORY_ID")));
				voList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return voList;
	}
	
	public Vector<MenuVo> getMenuListByCategoryId(int categoryId){
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM MENU WHERE CATEGORY_ID = ? ORDER BY MENU_ID ASC";

		Vector<MenuVo> voList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);
			rs = pstmt.executeQuery();

			voList = new Vector<MenuVo>();

			while (rs.next()) {
				MenuVo vo = new MenuVo(Integer.parseInt(rs.getString("MENU_ID")),
						rs.getString("MENU_NAME"),
						Integer.parseInt(rs.getString("PRICE")),
						Integer.parseInt(rs.getString("CATEGORY_ID")));
				voList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return voList;
	}
	
	// 메뉴 추가 (메뉴이름, 가격, 카테고리아이디)
	public void insertMenu(String menuName, int price, int categoryId) {
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO MENU VALUES"
				+ "((SELECT NVL(MAX(MENU_ID), 0) + 1 FROM MENU), ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menuName);
			pstmt.setInt(2, price);
			pstmt.setInt(3, categoryId);

			pstmt.execute();
			System.out.println("MenuDao: 1 행이 삽입 되었습니다.");

			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 메뉴 수정 (수정하려고하는 메뉴이름, 메뉴이름, 가격, 카테고리아이디)
	public void updateMenu(String currentMenuName, String menuName, int price, int categoryId) {
		PreparedStatement pstmt = null;

		String sql = "UPDATE MENU "
				+ "SET "
				+ "MENU_NAME = ?, PRICE = ?, CATEGORY_ID = ? "
				+ "WHERE MENU_NAME = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menuName);
			pstmt.setInt(2, price);
			pstmt.setInt(3, categoryId);
			pstmt.setString(4, currentMenuName);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("MenuDao: " + cnt + "건 수정되었습니다.");
			} else {
				System.out.println("MenuDao: 수정 실패 혹은 수정 사항 없음.");
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 메뉴 삭제 (메뉴이름)
	public void deleteMenu(String menuName) {
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM MENU "
				+ "WHERE MENU_NAME = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menuName);

			pstmt.execute();
			System.out.println("MenuDao: '" + menuName + "' 가(이) 삭제 되었습니다.");

			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
