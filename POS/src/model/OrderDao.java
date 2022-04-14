package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class OrderDao {
	Connection conn = null;

	public OrderDao() {
		conn = DBConn.getConnection();
	}

	// 주문추가
	public void insertOrder(String phoneNumber, Vector<OrderVo> orderVoList) {
		if (orderVoList == null) {
			System.err.println("orderVo == null!!");
			return;
		}

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;

		String sql = "INSERT INTO ORDERS VALUES((SELECT NVL(MAX(ORDER_ID), 0) + 1 FROM ORDERS), ?, DEFAULT)";
		String sql2 = "INSERT INTO ORDER_LIST VALUES"
				+ "((SELECT MAX(ORDER_ID) FROM ORDERS), ?, ?, ?)";

		MenuDao mDao = new MenuDao();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneNumber);

			pstmt.execute();
			
			pstmt2 = conn.prepareStatement(sql2);
			for (OrderVo vo : orderVoList) {
				MenuVo mVo = mDao.getMenuById(vo.getMenuId());
				int price = mVo.getPrice() * vo.getQueantity();

				pstmt2.setInt(1, vo.getMenuId());
				pstmt2.setInt(2, vo.getQueantity());
				pstmt2.setInt(3, price);
				pstmt2.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//
	public Vector<OrderListVo> getOrderListById(int orderId) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT (SELECT MENU_NAME FROM MENU WHERE MENU_ID = O.MENU_ID) MENU_NAME, "
					+ "QUANTITY, TOTAL_PRICE FROM ORDER_LIST O WHERE ORDER_ID = ?";
			Vector<OrderListVo> voList = null;

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, orderId);
				rs = pstmt.executeQuery();

				voList = new Vector<OrderListVo>();

				while (rs.next()) {
					OrderListVo vo = new OrderListVo(rs.getString("MENU_NAME"),
							Integer.parseInt(rs.getString("QUANTITY")),
							Integer.parseInt(rs.getString("TOTAL_PRICE")));
					voList.add(vo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return voList;
	}
}
