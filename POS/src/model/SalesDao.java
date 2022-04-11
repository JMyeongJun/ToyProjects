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
	
	// 
	public Vector<SalesVo> getSalesList(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		/*
		 * SELECT SALES_ID, ORDER_ID, PAYMENT_METHOD, USE_POINT, 
    	(SELECT SUM(TOTAL_PRICE) FROM ORDER_LIST WHERE ORDER_ID = S.ORDER_ID) SALES, SALES_DATE 
		FROM SALES S;
		 */
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
