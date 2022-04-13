package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.raven.datechooser.DateChooser;

import model.SalesDao;
import model.SalesVo;

public class SalesPanel extends BasicPanel implements ActionListener {
	MainFrame mf;
	
	JPanel bottom_bottom;
	JButton btnPrev, btnLookup, btnToExcel;
	JLabel lbl_Cash, lbl_Card, lbl_All, lbl_Order_List, lbl_Order_Number, lbl_Order_Date, lbl_P;
	JScrollPane scpane;
	static JTable table, table_Order;
	JFrame frame;

	GridBagLayout gbl;
	GridBagConstraints gbc;
	private DefaultTableModel model;
	private JTable table2;
	private final DateChooser date1 = new DateChooser();
	private final DateChooser date2 = new DateChooser();
	private JTextField textDate1;
	private JTextField textDate2;

	SalesDao dao = new SalesDao();

	public SalesPanel() {
		date2.setDateFormat("yyyy-MM-dd");
		date2.setFont(new Font("굴림", Font.PLAIN, 12));
		date2.setForeground(SystemColor.textHighlight);
		GroupLayout groupLayout = (GroupLayout) date1.getLayout();
		date1.setFont(new Font("굴림", Font.PLAIN, 12));
		date1.setDateFormat("yyyy-MM-dd");
		date1.setForeground(SystemColor.textHighlight);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 116, 116, 0 };
		gridBagLayout.rowHeights = new int[] { 21, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		top_bottom.setLayout(gridBagLayout);

		textDate1 = new JTextField();
		textDate1.setFont(new Font("굴림", Font.PLAIN, 13));
		textDate1.setLocale(Locale.KOREA);
		date1.setTextRefernce(textDate1);
		GridBagConstraints gbc_textDate1 = new GridBagConstraints();
		gbc_textDate1.fill = GridBagConstraints.VERTICAL;
		gbc_textDate1.anchor = GridBagConstraints.WEST;
		gbc_textDate1.insets = new Insets(0, 0, 0, 5);
		gbc_textDate1.gridx = 0;
		gbc_textDate1.gridy = 1;
		top_bottom.add(textDate1, gbc_textDate1);
		textDate1.setColumns(10);

		textDate2 = new JTextField();
		textDate2.setFont(new Font("굴림", Font.PLAIN, 13));
		date2.setTextRefernce(textDate2);
		textDate2.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textDate2.setColumns(10);
		GridBagConstraints gbc_textDate2 = new GridBagConstraints();
		gbc_textDate2.fill = GridBagConstraints.VERTICAL;
		gbc_textDate2.gridx = 1;
		gbc_textDate2.gridy = 1;
		top_bottom.add(textDate2, gbc_textDate2);
		initComponent();
	}
	
	public SalesPanel(MainFrame mf) {
		this();
		this.mf = mf;
		super.btnPrev.addActionListener(e -> {
			mf.changePanel(new HomePanel(mf));
		});
	}

	private void initComponent() {
		btnLookup = new JButton("조회");
		btnToExcel = new JButton("엑셀 저장");

		subTitle.setText("매출조회");
		subTitle.setFont(new Font("굴림", Font.PLAIN, 24));
		// subTitle.setHorizontalAlignment(SwingConstants.CENTER);
		top_top.setBackground(Color.WHITE);
		top_top.add(subTitle, BorderLayout.CENTER);

		// JTable
		table = new JTable();
		tableRefresh(table);
		table.setForeground(Color.WHITE);
		table.setBackground(Color.BLACK);
		table.setFont(new Font("굴림", Font.PLAIN, 12));

		DefaultTableCellRenderer c = new DefaultTableCellRenderer();
		TableColumnModel c2 = table.getColumnModel();
		for (int i = 0; i < c2.getColumnCount(); i++) {
			c2.getColumn(i).setCellRenderer(c);
		}

		scpane = new JScrollPane(table);
		scpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// bottom.setLayout(new BorderLayout());
		bottom.add(scpane, BorderLayout.NORTH);
		bottom.setLayout(new GridLayout(2, 0, 0, 0));

		// Scroll

		// JPanel 추가
		gbl = new GridBagLayout();

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		lbl_Cash = new JLabel("현금 결제 : ");
		lbl_Card = new JLabel("카드 결제 : ");
		lbl_All = new JLabel("총 매출액 : ");

		// JButton
		JButton btn1 = new JButton("조회");
		GridBagConstraints gbc_btn1 = new GridBagConstraints();
		gbc_btn1.insets = new Insets(0, 0, 0, 7);
		gbc_btn1.gridx = 5;
		gbc_btn1.gridy = 1;
		top_bottom.add(btn1, gbc_btn1);

		btn1.addActionListener(this);

		JButton btn2 = new JButton("엑셀 저장");
		GridBagConstraints gbc_btn2 = new GridBagConstraints();
		gbc_btn2.gridx = 7;
		gbc_btn2.gridy = 1;
		top_bottom.add(btn2, gbc_btn2);
		btn2.addActionListener(this);

		// side

		lbl_Order_List = new JLabel("주문 내역");
		lbl_Order_Number = new JLabel("주문 번호 : ");
		lbl_Order_Date = new JLabel("주문 일자 : ");
		lbl_P = new JLabel("결제 금액 : ");

		// GridBagConstraints gbc_lbl = new GridBagConstraints();
		// gbc_lbl.gridx = 0;
		// gbc_lbl.gridy = 2;

		lbl_Order_List = new JLabel("주문 내역");
		lbl_Order_List.setFont(new Font("굴림", Font.PLAIN, 26));
		GridBagConstraints gbc_lbl_Order_List = new GridBagConstraints();
		side.add(lbl_Order_List, gbc_lbl_Order_List);

		lbl_Order_Number = new JLabel("주문 번호 : ");
		GridBagConstraints gbc_lbl_Order_Number = new GridBagConstraints();
		side.add(lbl_Order_Number, gbc_lbl_Order_Number);

		lbl_Order_Date = new JLabel("주문 일자 : ");
		GridBagConstraints gbc_lbl_Order_Date = new GridBagConstraints();
		side.add(lbl_Order_Date, gbc_lbl_Order_Date);

		lbl_P = new JLabel("결제 금액");
		lbl_P.setFont(new Font("굴림", Font.PLAIN, 24));
		GridBagConstraints gbc_lbl_P = new GridBagConstraints();
		side.add(lbl_P, gbc_lbl_P);

		// side table
		// side.setLayout(gbl);
		String[] table_C = { "메뉴", "수량", "가격" };
		table_Order = new JTable(new DefaultTableModel(null, table_C));

		DefaultTableModel DTM = (DefaultTableModel) table_Order.getModel();

		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel center2 = table_Order.getColumnModel();
		for (int i = 0; i < center2.getColumnCount(); i++) {
			center2.getColumn(i).setCellRenderer(center);

			gbc.fill = GridBagConstraints.NONE;

		}
		
		bottom.add(table);

	}

	// 테이블 새로고침
	public static void tableRefresh(JTable table) {
		tableRefresh(table, "");
	}

	// 테이블 새로고침 - 날짜 조회
	private static void tableRefresh(JTable table, String searchData) {
		SalesDao dao = new SalesDao();
		table.setModel(new DefaultTableModel(getDataList(dao.getSalesList()), getColumnList()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		});
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.repaint();
	}

	// Row
	private static Vector<? extends Vector<String>> getDataList(Vector<SalesVo> voList) {
		Vector<Vector<String>> list = new Vector<Vector<String>>();

		for (SalesVo vo : voList) {
			Vector<String> row = new Vector<String>();

			row.add(String.valueOf(vo.getOrderId())); // 주문번호
			row.add(vo.getOrderDate()); // 주문일자
			row.add(vo.getPaymentMethod()); // 결제수단
			row.add(String.valueOf(vo.getSales())); // 결제 금액
			row.add(String.valueOf(vo.getUsePoint())); // 포인트사용
			row.add(String.valueOf(vo.getSales() + vo.getUsePoint())); // 매출

			list.add(row);
		}
		return list;
	}

	// column
	public static Vector<?> getColumnList() {
		Vector<String> cols = new Vector<String>();
		cols.add("주문번호");
		cols.add("주문일자");
		cols.add("결제수단");
		cols.add("결제금액");
		cols.add("포인트사용");
		cols.add("매출");
		return cols;
	}

	// 테이블 getter
	public static JTable getTable() {
		return table;
	}

	private void gbAdd(JComponent c, int x, int y, int w, int h) {

		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(c, gbc);
	}

	// salesId, orderId usePoint sales orderDate
	// salesDate paymentMethod
	/*
	 * public void bottomView() { Vector<SalesVo> list = dao.getSalesList();
	 * String[] columnName = { "주문번호", "주문일자", "결제수단", "결제금액", "포인트사용", "매출"};
	 * String[][] rowData = new String[list.size()][columnName.length]; for (int
	 * row=0; row<rowData.length; row++) { rowData[row][0] =
	 * Integer.toString(list.get(row).getOrderId()); rowData[row][1] =
	 * list.get(row).getOrderDate(); rowData[row][2] =
	 * list.get(row).getPaymentMethod(); rowData[row][3] = null; rowData[row][4] =
	 * Integer.toString(list.get(row).getUsePoint()); rowData[row][5] =
	 * Integer.toString(list.get(row).getSales());
	 * 
	 * }
	 * 
	 * model = new DefaultTableModel(rowData, columnName); table = new
	 * JTable(model); scpane = new JScrollPane( table );
	 * scpane.setVerticalScrollBarPolicy(ScrollPaneConstants.
	 * VERTICAL_SCROLLBAR_ALWAYS); bottom.add( scpane,BorderLayout.NORTH );
	 * bottom.setLayout(new GridLayout(2, 0, 0, 0));
	 * 
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "조회":
			System.out.println("조회 누름");

			break;
		case "엑셀 저장":
			System.out.println("엑셀 저장 누름");
			break;
		}

	}

}