package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Row;

import com.raven.datechooser.DateChooser;

import model.SalesDao;
import model.SalesVo;

public class SalesPanel extends BasicPanel implements ActionListener {
	MainFrame mf;

	JPanel bottom_bottom;
	JButton btnLookup, btnToExcel;
	JLabel lbl_Cash, lbl_Card, lbl_All, lbl_Order_List, lbl_Order_Number, lbl_Order_Date, lbl_P;
	JLabel lbl_CashTot, lbl_CardTot, lbl_Tot;
	JLabel lbl_Get_Order_Number, lbl_Get_Order_Date, lbl_Get_Price;
	JScrollPane scpane;
	JTable table;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	DateChooser date1;
	DateChooser date2;
	JTextField textDate1;
	JTextField textDate2;

	public SalesPanel() {
		initComponent();
	}

	public SalesPanel(MainFrame mf) {
		this();
		this.mf = mf;
		btnPrev.addActionListener(e -> {
			 mf.changePanel(new HomePanel(mf));
		});
	}

	private void initComponent() {
		subTitle.setText("매출조회");

		// top_bottom
		date1 = new DateChooser();
		date1.setDateFormat("yyyy-MM-dd");

		date2 = new DateChooser();
		date2.setDateFormat("yyyy-MM-dd");

		textDate1 = new JTextField();
		textDate1.setFont(new Font("굴림", Font.PLAIN, 13));
		date1.setTextRefernce(textDate1);
		textDate1.setColumns(10);

		textDate2 = new JTextField();
		textDate2.setFont(new Font("굴림", Font.PLAIN, 13));
		date2.setTextRefernce(textDate2);
		textDate2.setColumns(10);

		top_bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		top_bottom.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		top_bottom.add(textDate1);
		top_bottom.add(new JLabel("~"));
		top_bottom.add(textDate2);

		// bottom
		// bottom : bottom_bottom
		bottom_bottom = new JPanel();
		lbl_Cash = new JLabel("현금 결제 : ");
		lbl_Card = new JLabel("카드 결제 : ");
		lbl_All = new JLabel("총 매출액 : ");
		lbl_CashTot = new JLabel();
		lbl_CardTot = new JLabel();
		lbl_Tot = new JLabel();

		bottom_bottom.add(lbl_Cash);
		bottom_bottom.add(lbl_CashTot);
		bottom_bottom.add(lbl_Card);
		bottom_bottom.add(lbl_CardTot);
		bottom_bottom.add(lbl_All);
		bottom_bottom.add(lbl_Tot);

		// bottom : JTable
		table = new JTable();
		tableRefresh(table, LocalDate.now().toString(), LocalDate.now().toString()); // 당일 매출 테이블로 초기화
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				
//				int c = table.getSelectedColumn(); // 선택된 칼럼
//				int r = table.getSelectedRow(); // 선택된 줄
//				String clicked_Order_Num = (String) table.getValueAt(r, 0);
//				String clicked_Order_Date = (String )table.getValueAt(r, 1);
//				String clicked_Order_Sales = (String) table.getValueAt(r, 5);
//				System.out.println("클릭한 주문번호: " + clicked_Order_Num);
//				System.out.println("클릭한 주문날짜: " + clicked_Order_Date);
//				System.out.println("클릭한 결제금액: " + clicked_Order_Sales);
				getClicked();
			}
		});
		scpane = new JScrollPane(table);
		scpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		////////////////////////////////////////////////////////////////////////////

		table.setRowHeight(30);
		scpane.setSize(500, 700);
		table.setSize(500, 800);

		// bottom.setLayout(new GridLayout(2, 0, 0, 0));
		bottom.setLayout(new GridBagLayout());

		gbl = new GridBagLayout();
		bottom.setLayout(gbl);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		gbAddb(scpane, 0, 0, 1, 1);
		gbAddb(bottom_bottom, 0, 1, 2, 2);
		////////////////////////////////////////////////////////////////////////////
		// top_bottom
		btnLookup = new JButton("조회");
		btnToExcel = new JButton("엑셀 저장");
		btnLookup = new JButton("조회");
		GridBagConstraints gbc_btn1 = new GridBagConstraints();
		gbc_btn1.gridx = 5;
		gbc_btn1.gridy = 1;
		top_bottom.add(btnLookup, gbc_btn1);
		btnLookup.addActionListener(this);

		btnToExcel = new JButton("엑셀 저장");
		GridBagConstraints gbc_btn2 = new GridBagConstraints();
		gbc_btn2.insets = new Insets(0, 5, 0, 5);
		gbc_btn2.gridx = 7;
		gbc_btn2.gridy = 1;
		top_bottom.add(btnToExcel, gbc_btn2);
		btnToExcel.addActionListener(this);

		// side //////////////////////////////////////////////////
		gbl = new GridBagLayout();
		side.setLayout(gbl);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

//      SalesDao sdao = new SalesDao();
//      SalesVo  svovo;
//      Vector<SalesVo> vsvo = new Vector<SalesVo>();
//      svovo.getOrderId();

		lbl_Order_List = new JLabel("주문 내역");
		gbAdd(lbl_Order_List, 0, 0, 1, 1);

		lbl_Order_Number = new JLabel("주문 번호 : ");
		gbAdd(lbl_Order_Number, 0, 1, 1, 1);
		lbl_Get_Order_Number = new JLabel();
		gbAdd(lbl_Get_Order_Number, 1, 1, 1, 1);

		lbl_Order_Date = new JLabel("주문 일자 : ");
		gbAdd(lbl_Order_Date, 0, 2, 1, 1);
		lbl_Get_Order_Date = new JLabel();
		gbAdd(lbl_Get_Order_Date, 1, 2, 1, 1);

		lbl_P = new JLabel("결제 금액 : ");
		gbAdd(lbl_P, 0, 5, 1, 1);
		lbl_Get_Price = new JLabel();
		gbAdd(lbl_Get_Price, 1, 5, 1, 1);

		// side.add(lbl_Order_List);
		// side.add(lbl_Order_Number);
		// side.add(lbl_Order_Date);
		// side.add(lbl_P);

		//////////////////////////////////////////////////////////
	}

	private String getToInfo() {

		return null;
	}

	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		side.add(c, gbc);

	}

	private void gbAddb(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		bottom.add(c, gbc);

	}

// 테이블 새로고침 - 날짜 조회
	private void tableRefresh(JTable table, String date1, String date2) {
		SalesDao sdao = new SalesDao();
		table.setModel(new DefaultTableModel(getDataList(sdao.getSalesList(date1, date2)), getColumnList()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.repaint();

		setSalesLabelText(date1, date2);
	}

	// bottom_bottom 패널 라벨 set text
	private void setSalesLabelText(String date1, String date2) {
		SalesDao sdao = new SalesDao();
		Vector<SalesVo> voList = sdao.getSalesList(date1, date2);
		if (voList.size() > 0) {
			lbl_CashTot.setText(Integer.toString(getTotCash(voList)));
			lbl_CardTot.setText(Integer.toString(getTotCard(voList)));
			lbl_Tot.setText(Integer.toString(getTotCash(voList) + getTotCard(voList)));
		}

		bottom_bottom.revalidate();
		bottom_bottom.repaint();
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

	// 전체 Row
	private Vector<? extends Vector<String>> getDataList(Vector<SalesVo> voList) {
		Vector<Vector<String>> list = new Vector<Vector<String>>();

		for (SalesVo vo : voList) {
			Vector<String> row = new Vector<String>();

			row.add(String.valueOf(vo.getOrderId())); // 주문번호
			row.add(vo.getOrderDate()); // 주문일자
			row.add(vo.getPaymentMethod()); // 결제수단
			row.add(String.valueOf(vo.getSales() - vo.getUsePoint())); // 결제 금액
			row.add(String.valueOf(vo.getUsePoint())); // 포인트사용
			row.add(String.valueOf(vo.getSales())); // 매출

			list.add(row);
		}
		return list;
	}

	// 테이블 클릭했을 때
	private String getClicked() {
		int c = table.getSelectedColumn(); // 선택된 칼럼
		int r = table.getSelectedRow(); // 선택된 줄
		String clicked_Order_Num = (String) table.getValueAt(r, 0);
		String clicked_Order_Date = (String )table.getValueAt(r, 1);
		String clicked_Order_Sales = (String) table.getValueAt(r, 5);
		System.out.println("클릭한 주문번호: " + clicked_Order_Num);
		System.out.println("클릭한 주문날짜: " + clicked_Order_Date);
		System.out.println("클릭한 결제금액: " + clicked_Order_Sales);
		
		lbl_Get_Order_Number.setText(clicked_Order_Num);
		lbl_Get_Order_Date.setText(clicked_Order_Date);
		lbl_Get_Price.setText(clicked_Order_Sales);
		return clicked_Order_Num;
	}

	// 현금
	private int getTotCash(Vector<SalesVo> voList) {
		int tot = 0;
		for (SalesVo vo : voList) {
			if (vo.getPaymentMethod().contains("현금"))
				tot = tot + vo.getSales();
		}
		return tot;
	}

	// 카드
	private int getTotCard(Vector<SalesVo> voList) {
		int tot = 0;
		for (SalesVo vo : voList) {
			if (vo.getPaymentMethod().contains("카드"))
				tot = tot + vo.getSales();
		}
		return tot;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "조회":
			System.out.println("조회 누름");
			tableRefresh(table, getSelectedDate(date1), getSelectedDate(date2));
			break;
		case "엑셀 저장":
			System.out.println("엑셀 저장 누름");
			break;
		}
	}

	// 날짜 데이터 String으로
	private String getSelectedDate(DateChooser date) {
		String first_Year = String.valueOf(date.getSelectedDate().getYear());
		String first_Month = String.valueOf(date.getSelectedDate().getMonth());
		String first_Day = String.valueOf(date.getSelectedDate().getDay());

		String ret = first_Year + "-" + first_Month + "-" + first_Day;
		System.out.println(ret);
		return ret;
	}

}