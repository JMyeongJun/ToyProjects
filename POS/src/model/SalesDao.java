package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class SalesDao {
	Connection conn = null;

	public SalesDao() {
		conn = DBConn.getConnection();
	}
	
	// 해당 날짜 사이 매출 조회
	public Vector<SalesVo> getSalesList(String date1, String date2){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT SALES_ID, ORDER_ID, USE_POINT, (SELECT SUM(TOTAL_PRICE) FROM ORDER_LIST WHERE ORDER_ID = S.ORDER_ID) SALES, "
				+ "(SELECT ORDER_DATE FROM ORDERS WHERE ORDER_ID = S.ORDER_ID) ORDER_DATE, SALES_DATE, PAYMENT_METHOD "
				+ "FROM SALES S "
				+ "WHERE (SELECT ORDER_DATE FROM ORDERS WHERE ORDER_ID = S.ORDER_ID) BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || '23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "ORDER BY SALES_ID";

		Vector<SalesVo> voList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			
			rs = pstmt.executeQuery();

			voList = new Vector<SalesVo>();

			while (rs.next()) {
				SalesVo vo = new SalesVo(Integer.parseInt(rs.getString("SALES_ID")),
						Integer.parseInt(rs.getString("ORDER_ID")),
						Integer.parseInt(rs.getString("USE_POINT")),
						Integer.parseInt(rs.getString("SALES")),
						rs.getString("ORDER_DATE"),
						rs.getString("SALES_DATE"),
						rs.getString("PAYMENT_METHOD"));
				voList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return voList;
	}
	
	// 모든 매출 조회
	public Vector<SalesVo> getSalesList(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT SALES_ID, ORDER_ID, USE_POINT, (SELECT SUM(TOTAL_PRICE) FROM ORDER_LIST WHERE ORDER_ID = S.ORDER_ID) SALES, "
				+ "(SELECT ORDER_DATE FROM ORDERS WHERE ORDER_ID = S.ORDER_ID) ORDER_DATE, SALES_DATE, PAYMENT_METHOD "
				+ "FROM SALES S ORDER BY SALES_ID";

		Vector<SalesVo> voList = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			voList = new Vector<SalesVo>();

			while (rs.next()) {
				SalesVo vo = new SalesVo(Integer.parseInt(rs.getString("SALES_ID")),
						Integer.parseInt(rs.getString("ORDER_ID")),
						Integer.parseInt(rs.getString("USE_POINT")),
						Integer.parseInt(rs.getString("SALES")),
						rs.getString("ORDER_DATE"),
						rs.getString("SALES_DATE"),
						rs.getString("PAYMENT_METHOD"));
				voList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return voList;
	}
	
	// 매출 기록 - OrderDao : insertOrder() 하고 바로 할것
	public void insertSales(String paymentMethod, int usePoint) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		String sql = "INSERT INTO SALES VALUES ( "
				+ "(SELECT NVL(MAX(SALES_ID), 0) + 1 FROM SALES), "
				+ "(SELECT MAX(ORDER_ID) FROM ORDERS), "
				+ "?, "
				+ "?, "
				+ "DEFAULT)";
		String sql2 = "UPDATE MEMBERS SET POINT = POINT + "
				+ "ROUND( (SELECT SUM(TOTAL_PRICE) FROM ORDER_LIST WHERE ORDER_ID = (SELECT MAX(ORDER_ID) FROM ORDERS)) * 0.01) "
				+ "-?"
				+ "WHERE PHONE_NUMBER = (SELECT PHONE_NUMBER FROM ORDERS WHERE ORDER_ID = (SELECT MAX(ORDER_ID) FROM ORDERS))";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paymentMethod);
			pstmt.setInt(2, usePoint);
			
			pstmt.execute();
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, usePoint);
			pstmt2.execute();
			System.out.println("SalesDao: 1건 삽입 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
