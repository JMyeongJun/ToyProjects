package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
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
	JLabel labelTotalPrice, labelUsePoint, labelTxtPoint, labelPayment, labelTxtTotal, labelTxtPay;
	JButton btnSavePoint, btnCash, btnCard, btnUsePoint;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	MemberVo member = null;
	
	MainFrame mf;

	public PayPanel() {
		super(1.3);
		initcomponents();
	}
	
	public PayPanel(MainFrame mf, JTable table) {
		super(1.3);
		this.mf = mf;
		this.table = table;
		btnPrev.addActionListener(e -> {
			mf.changePanel(new OrderPanel(mf));
		});
		initcomponents();
	}

	private void initcomponents() {
		subTitle.setFont(new Font("본고딕", Font.BOLD, 50));
		subTitle.setText("결제");
		
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		bottom.setLayout(gbl);
		JLabel lblOrder = new JLabel("주문 내역");

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0; 

		gbAdd(lblOrder, 0, 0, 1, 1);
		bottom.add(lblOrder);

		gbc.weighty = 20.0; 
		gbAdd(table,0,1,1,1);
		bottom.add(table);

		side.setLayout(gbl);

		labelTxtTotal   = new JLabel("총액");
		labelTxtTotal.setFont(basicFont(20));
		labelTotalPrice = new JLabel(Integer.toString(getTotalPrice()));
		labelTotalPrice.setFont(basicFont(20));
		labelTxtPoint   = new JLabel("포인트 사용");
		labelTxtPoint.setFont(basicFont(20));
		btnSavePoint    = new JButton("적립");
		btnSavePoint.setFont(basicFont(20));
		labelUsePoint   = new JLabel("0");
		labelUsePoint.setFont(basicFont(20));
		labelTxtPay     = new JLabel("결제 금액");
		labelTxtPay.setFont(basicFont(25));
		labelPayment    = new JLabel(Integer
				.toString( Integer.parseInt( labelTotalPrice.getText() ) - Integer.parseInt( labelUsePoint.getText() ))); 
		labelPayment.setFont(basicFont(30));
		btnCash         = new JButton("현금 결제");
		btnCash.setFont(basicFont(20));
		btnCard         = new JButton("카드 결제");
		btnCard.setFont(basicFont(20));
		btnUsePoint     = new JButton("포인트 사용");
		btnUsePoint.setFont(basicFont(20));


		gbc.weighty = 1.0; 
		gbc.insets = new Insets(2,20,2,2);
		gbAdd(labelTxtTotal,  0,0,1,1);
		gbAdd(labelTotalPrice,0,1,1,1);
		gbc.weightx = 3.0;
		gbAdd(labelTxtPoint,  0,2,10,1);

		gbc.weightx = 1.0;
		gbc.insets = new Insets(50,200,30,50);  
		gbAdd(btnSavePoint,   9,2,1,1);

		gbc.insets = new Insets(2,20,2,2);
		gbAdd(labelUsePoint,  0,3,1,1);
		gbAdd(labelTxtPay,    0,4,1,1);
		gbAdd(labelPayment,   0,5,1,1);

		gbc.weighty = 1.5; 
		gbc.insets = new Insets(10,30,10,30);
		gbAdd(btnCash,        0,6,10,1);
		gbAdd(btnCard,        0,7,10,1);
		gbAdd(btnUsePoint,    0,8,10,1);

		side.add(labelTxtTotal);
		side.add(labelTotalPrice);
		side.add(labelTxtPoint);
		side.add(btnSavePoint);
		side.add(labelUsePoint);
		side.add(labelTxtPay);
		side.add(labelPayment);

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
			btnSavePoint.setEnabled(false);
		});
		
		btnUsePoint.addActionListener(e -> {
			int usePoint = Integer.parseInt(JOptionPane.showInputDialog("사용가능 포인트 : " + Integer.toString(member.getPoint())));
			System.out.println(usePoint);
			if(usePoint > member.getPoint()) {
				System.err.println("사용가능 포인트 확인");
				JOptionPane.showMessageDialog(null, "사용가능 포인트 확인", "오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
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
			mf.changePanel(new OrderPanel(mf));
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
			mf.changePanel(new OrderPanel(mf));
		});
	}
	
	private void gbAdd(JComponent c, int x, int y, int w, int h) {
	      gbc.gridx = x;
	      gbc.gridy = y;
	      gbc.gridwidth = w;
	      gbc.gridheight = h;
	      gbl.setConstraints(c, gbc);
	      add(c);
	   }

}
