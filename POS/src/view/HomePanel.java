package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends JPanel implements ActionListener {
	MainFrame mf;
	JButton btnOrder, btnMember, btnMenu, btnSales;
	JLabel lblTitle;
	GridBagLayout gb;
	GridBagConstraints gbc;

	public HomePanel(MainFrame mf) {
		this.mf = mf;
		initcomponent();
	}

	private void initcomponent() {
		lblTitle = new JLabel("pos");
		btnOrder = new JButton("주문");
		btnMember = new JButton("회원 관리");
		btnMenu = new JButton("메뉴 관리");
		btnSales = new JButton("매출 조회");

		lblTitle.setFont(new Font("본고딕", Font.BOLD, 40));
		btnDesign(btnOrder);
		btnDesign(btnMember);
		btnDesign(btnMenu);
		btnDesign(btnSales);
		
		btnOrder.addActionListener(this);
		btnMember.addActionListener(this);
		btnMenu.addActionListener(this);
		btnSales.addActionListener(this);
		
		gb = new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		setLayout(gb);
		setBackground(Color.white);
		
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(5, 55, 0, 0); // 상,좌,하,우
		gbAdd(lblTitle, 0, 0, 1, 1);

		gbc.weightx = 6.0;
		gbc.weighty = 2;
		gbc.insets = new Insets(5, 10, 45, 200); // 상,좌,하,우
		gbAdd(btnOrder, 1, 1, 1, 1);
		gbAdd(btnMember, 1, 2, 1, 1);
		gbAdd(btnMenu, 1, 3, 1, 1);
		gbAdd(btnSales, 1, 4, 1, 1);
	}

	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
		add(c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		switch (e.getActionCommand()) {
		case "주문":
			mf.changePanel(new OrderPanel(mf));
			break;
		case "회원 관리":
			mf.changePanel(new MemberPanel(mf));
			break;
		case "메뉴 관리":
			mf.changePanel(new MenuPanel(mf));
			break;
		case "매출 조회":
			mf.changePanel(new SalesPanel(mf));
			break;
		default:
			break;
		}
	}

	private void btnDesign(JButton btn) {
		Color btnBackCol = new Color(52, 152, 219);
		Color btnForeCol = Color.WHITE;
		Font btnFont = new Font("본고딕", Font.BOLD, 40);

		btn.setBackground(btnBackCol);
		btn.setForeground(btnForeCol);
		btn.setFont(btnFont);
	}

}
