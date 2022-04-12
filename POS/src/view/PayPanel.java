package view;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.MemberDao;
import model.MemberVo;
import model.MenuDao;
import model.OrderDao;
import model.OrderVo;
import model.SalesDao;

public class PayPanel extends BasicPanel {
	JTable table;
	JLabel labelTotalPrice, labelUsePoint, labelTxtPoint, labelPayment;
	JButton btnSavePoint, btnCash, btnCard, btnUsePoint;
	
	MemberVo member;
	
	MainFrame mf;

	public PayPanel(MainFrame mf) {
		super(1.3);
		this.mf = mf;
		initcomponents();
	}

	private void initcomponents() {
		subTitle.setText("결제");
		bottom.setLayout(new BorderLayout());

		bottom.add(new JLabel("주문 내역"), BorderLayout.NORTH);

		table = OrderPanel.getTable();
//		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		tableRefresh(table);
		bottom.add(table, BorderLayout.CENTER);

		labelTotalPrice = new JLabel(Integer.toString(getTotalPrice()));
		side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
		side.add(new JLabel("총액"));
		side.add(labelTotalPrice);
		labelTxtPoint = new JLabel("포인트 사용");
		side.add(labelTxtPoint);
		btnSavePoint = new JButton("적립");
		side.add(btnSavePoint);
		labelUsePoint = new JLabel("0");
		side.add(labelUsePoint);
		side.add(new JLabel("결제 금액"));
		labelPayment = new JLabel(Integer
				.toString(Integer.parseInt(labelTotalPrice.getText()) - Integer.parseInt(labelUsePoint.getText())));
		side.add(labelPayment);
		btnCash = new JButton("현금 결제");
		btnCard = new JButton("카드 결제");
		btnUsePoint = new JButton("포인트 사용");
		side.add(btnCash);
		side.add(btnCard);
		side.add(btnUsePoint);

		setBtnActions();
	}
	
	private int getTotalPrice() {
		int sum = 0;
		
		MenuDao mdao = new MenuDao();
		
		
		for(int i = 0; i < table.getRowCount(); i++) {
			int price = mdao.getMenuByName((String)table.getModel().getValueAt(i, 0)).getPrice();
			int quantity = (int)table.getModel().getValueAt(i, 1);
			
			sum += price * quantity;
		}
		
		return sum;
	}

	private void setBtnActions() {
		btnSavePoint.addActionListener(e -> {
			String phoneNumber = JOptionPane.showInputDialog("휴대폰 번호 입력");
			System.out.println(phoneNumber);
			
			MemberDao dao = new MemberDao();
			member = dao.getMember(phoneNumber);
			
			System.out.println(member);
			labelTxtPoint.setText(labelTxtPoint.getText() + " (" + member.getMemberName() + ")");
		});
		
		btnUsePoint.addActionListener(e -> {
			int usePoint = Integer.parseInt(JOptionPane.showInputDialog("사용가능 포인트 : " + Integer.toString(member.getPoint())));
			System.out.println(usePoint);
			labelUsePoint.setText(Integer.toString(usePoint));
			labelPayment.setText(Integer
					.toString(Integer.parseInt(labelTotalPrice.getText()) - Integer.parseInt(labelUsePoint.getText())));
		});
		
		btnCash.addActionListener(e -> {
			OrderDao odao = new OrderDao();
			SalesDao sdao = new SalesDao();
			MenuDao mdao = new MenuDao();

			Vector<OrderVo> voList = new Vector<OrderVo>();

			for(int i = 0; i < table.getRowCount(); i++) {
				int menuId = mdao.getMenuByName((String)table.getModel().getValueAt(i, 0)).getMenuID();
				int quantity = (int)table.getModel().getValueAt(i, 1);
				
				voList.add(new OrderVo(menuId, quantity));
			}
			
			if(member == null) {
				odao.insertOrder("", voList);
			}else {
				odao.insertOrder(member.getPhoneNumber(), voList);
			}
			sdao.insertSales(e.getActionCommand(), Integer.parseInt(labelUsePoint.getText()));
			
			JOptionPane.showMessageDialog(null, "현금결제 되었습니다.");
		});
		
		btnCard.addActionListener(e -> {
			OrderDao odao = new OrderDao();
			SalesDao sdao = new SalesDao();
			MenuDao mdao = new MenuDao();

			Vector<OrderVo> voList = new Vector<OrderVo>();

			for(int i = 0; i < table.getRowCount(); i++) {
				int menuId = mdao.getMenuByName((String)table.getModel().getValueAt(i, 0)).getMenuID();
				int quantity = (int)table.getModel().getValueAt(i, 1);
				
				voList.add(new OrderVo(menuId, quantity));
			}
			
			if(member == null) {
				odao.insertOrder("", voList);
			}else {
				odao.insertOrder(member.getPhoneNumber(), voList);
			}
			sdao.insertSales(e.getActionCommand(), Integer.parseInt(labelUsePoint.getText()));
			
			JOptionPane.showMessageDialog(null, "카드 결제 되었습니다.");
		});
	}

}
