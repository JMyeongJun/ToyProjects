package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.CategoryDao;
import model.CategoryVo;
import model.MenuDao;
import model.MenuVo;

public class OrderPanel extends BasicPanel implements ActionListener, MouseListener {

	// Field
	MainFrame mf;
	private GridBagLayout gb;
	private GridBagConstraints gbc;
	private GridLayout gl;

	public JLabel lblOrder;
	public JTable tblOrder;
	public JButton btn_DelMenu, btn_MnsMenu, btn_PlsMenu, btn_Payment, testbtn;
	Vector<JButton> btnCategory, btnMenu;

	// 생성자
	public OrderPanel() {
		initComponent();
	}

	public OrderPanel(MainFrame mf) {
		this();
		this.mf = mf;
		btnPrev.addActionListener(e -> {
			mf.changePanel(new HomePanel(mf));
		});
	}

	// 전체적인 레이아웃 설정
	private void initComponent() {
		subTitle.setText("주문");

		gl = new GridLayout(1, 0, 5, 0);
		gb = new GridBagLayout();
		gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		top_bottom.setLayout(gb);
		bottom.setLayout(new GridLayout(0, 5, 10, 10));
		bottom.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		side.setLayout(gb);

		viewBottom();
		viewBasic();
		viewSide();
	}

	// GridBag 레이아웃 설정
	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
	}

	public void gbAdd(GridBagLayout gb, GridBagConstraints gbc, JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
	}

	// Bottom -> 초기화면
	private void viewBasic() {

		CategoryDao cdao = new CategoryDao();
		MenuDao mdao = new MenuDao();
		Vector<CategoryVo> vo = cdao.getCategoryList();
		Vector<MenuVo> mvoList = null;

		if (vo.size() != 0) {

			mvoList = mdao.getMenuListByCategoryId(vo.get(0).getCategoryID());
			btnMenu = new Vector<JButton>();
			for (MenuVo mvo : mvoList) {
				btnMenu.add(new JButton(mvo.getMenuName()));
			}

			// 메뉴 버튼 생성
			for (JButton btnM : btnMenu) {
				btnM.setPreferredSize(new Dimension(45, 28));
				btnM.setBackground(colorMenu);
				btnM.setForeground(Color.WHITE);
				btnM.setFont(basicFont(20, Font.BOLD));
				btnM.addActionListener(new click_Menu());
				bottom.add(btnM);
			}
			spacePanel(bottom);
		}
	}

	// Top_Bottom 패널 & Bottom 패널
	private void viewBottom() {
		bottom.removeAll();

		btnCategory = new Vector<JButton>();
		CategoryDao cdao = new CategoryDao();
		gbc.insets = new Insets(5, 5, 5, 5);

		for (CategoryVo cvo : cdao.getCategoryList()) {
			btnCategory.add(new JButton(cvo.getCategoryName()));
		}

		int j = 0;
		for (JButton btn : btnCategory) {
			btn.setBackground(colorCategory);
			btn.setForeground(Color.WHITE);
			btn.setFont(basicFont(20, Font.BOLD));
			btn.addActionListener(e -> {
				btnMenu = new Vector<JButton>();
				bottom.removeAll();
				MenuDao mdao = new MenuDao();
				Vector<MenuVo> mvoList = mdao
						.getMenuListByCategoryId(cdao.getCategoryByName(e.getActionCommand()).getCategoryID());

				for (MenuVo mvo : mvoList) {
					btnMenu.add(new JButton(mvo.getMenuName()));
				}
				for (JButton btnM : btnMenu) {
					btnM.addActionListener(new click_Menu());
					btnM.setBackground(colorMenu);
					btnM.setForeground(Color.WHITE);
					btnM.setFont(new Font("본고딕", Font.BOLD, 20));
					bottom.add(btnM);
				}
				spacePanel(bottom);
				bottom.revalidate();
				bottom.repaint();
			});

			gbAdd(btn, j, 0, 1, 1);
			top_bottom.add(btn);
			j++;
		}
	}

	// Side 패널
	private void viewSide() {

		// Side - 테이블 설정
		String[] tbl_Column = { "메뉴", "수량", "가격" };
		tblOrder = new JTable();

		tblOrder.setModel(new DefaultTableModel(null, tbl_Column) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		JScrollPane scroll = new JScrollPane(tblOrder);

		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel center2 = tblOrder.getColumnModel();
		for (int i = 0; i < center2.getColumnCount(); i++) {
			center2.getColumn(i).setCellRenderer(center);
		}

		tblOrder.getParent().setBackground(Color.WHITE);
		tblOrder.setFont(basicFont(20));
		tblOrder.setRowHeight(30);
		tblOrder.setShowVerticalLines(false);
		tblOrder.setShowHorizontalLines(false);

		// Side - 나머지 설정
		lblOrder = new JLabel("주문내역");
		btn_DelMenu = new JButton("삭제");
		btn_MnsMenu = new JButton("-");
		btn_PlsMenu = new JButton("+");
		btn_Payment = new JButton("결제");

		gbc.weighty = 1.1;
		gbAdd(lblOrder, 0, 0, 3, 1);

		gbc.weighty = 6;
		gbAdd(scroll, 0, 1, 3, 1);

		gbc.weighty = 0.5;
		gbAdd(btn_DelMenu, 0, 2, 1, 1);
		gbAdd(btn_MnsMenu, 1, 2, 1, 1);
		gbAdd(btn_PlsMenu, 2, 2, 1, 1);
		gbc.insets = new Insets(0, 0, 0, 0);
		gbAdd(btn_Payment, 0, 3, 3, 1);

		side.add(lblOrder);
		side.add(scroll);
		side.add(btn_DelMenu);
		side.add(btn_MnsMenu);
		side.add(btn_PlsMenu);
		side.add(btn_Payment);

		lblOrder.setFont(basicFont(40, Font.BOLD));

		Color btn_side_col = new Color(229, 239, 204);
		btn_DelMenu.setBackground(btn_side_col);
		btn_MnsMenu.setBackground(btn_side_col);
		btn_PlsMenu.setBackground(btn_side_col);
		btn_Payment.setBackground(btn_side_col);

		tblOrder.addMouseListener(this);
		btn_DelMenu.addActionListener(this);
		btn_MnsMenu.addActionListener(this);
		btn_PlsMenu.addActionListener(this);
		btn_Payment.addActionListener(this);
	}

	// 빈패널 삽입 함수
	public void spacePanel(JPanel panel) {
		for (int i = panel.getComponentCount(); i < 15; i++) {
			JPanel emptyPanel = new JPanel();
			emptyPanel.setBackground(colorBottom);
			panel.add(emptyPanel);
		}
	}

	// 메뉴 클릭 이벤트
	class click_Menu implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e2) {

			MenuDao dao = new MenuDao();
			String btn_MenuName = e2.getActionCommand();
			DefaultTableModel model = ((DefaultTableModel) tblOrder.getModel());

			for (MenuVo vo : dao.getMenuList()) {

				if (btn_MenuName.equals(vo.getMenuName())) {

					if (model.getRowCount() == 0) {
						model.addRow(new Object[] { btn_MenuName, 1, vo.getPrice() });
					} else {

						int dupl = 0;
						for (int j = 0; j < model.getRowCount(); j++) {

							if (tblOrder.getValueAt(j, 0).equals(btn_MenuName)) {
								int pre_Quantity = (int) tblOrder.getValueAt(j, 1);
								tblOrder.setValueAt(pre_Quantity + 1, j, 1);
								int pre_Price = (int) tblOrder.getValueAt(j, 2);
								tblOrder.setValueAt(pre_Price + vo.getPrice(), j, 2);
							} else
								dupl++;
						}

						if (dupl == model.getRowCount()) {
							model.addRow(new Object[] { btn_MenuName, 1, vo.getPrice() });
						}
					}
				}
			}
		}
	}

	// 테이블 클릭 이벤트
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tblOrder.getSelectedRow();
		System.out.println("수량: " + tblOrder.getModel().getValueAt(row, 1) + "개");
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	// Side버튼 클릭 이벤트
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "삭제":
			click_DelMenu();
			break;
		case "+":
			click_PlsMenu();
			break;
		case "-":
			click_MnsMenu();
			break;
		case "결제":
			click_Payment();
			break;
		}
	}

	// Side -> 삭제 버튼 이벤트
	private void click_DelMenu() {

		int row = tblOrder.getSelectedRow();
		DefaultTableModel model = ((DefaultTableModel) tblOrder.getModel());

		if (row >= 0) {

			model.removeRow(row);

		} else
			System.out.println("행이 선택되지 않음");
	}

	// Side -> (+) 버튼 이벤트
	private void click_PlsMenu() {

		int row = tblOrder.getSelectedRow();
		DefaultTableModel model = ((DefaultTableModel) tblOrder.getModel());

		if (row >= 0) {

			MenuDao dao = new MenuDao();
			MenuVo vo = dao.getMenuByName((String) model.getValueAt(row, 0));
			int price = vo.getPrice();

			int pre_Quantity = (int) model.getValueAt(row, 1);
			int pre_Price = (int) model.getValueAt(row, 2);

			model.setValueAt(pre_Quantity + 1, row, 1);
			model.setValueAt(pre_Price + price, row, 2);

		} else
			System.out.println("행이 선택되지 않음");
	}

	// Side -> (-) 버튼 이벤트
	private void click_MnsMenu() {

		int row = tblOrder.getSelectedRow();
		DefaultTableModel model = ((DefaultTableModel) tblOrder.getModel());

		if (row >= 0) {

			MenuDao dao = new MenuDao();
			MenuVo vo = dao.getMenuByName((String) model.getValueAt(row, 0));
			int price = vo.getPrice();

			int pre_Quantity = (int) model.getValueAt(row, 1);
			int pre_Price = (int) model.getValueAt(row, 2);

			model.setValueAt(pre_Quantity - 1, row, 1);
			model.setValueAt(pre_Price - price, row, 2);

			if (pre_Quantity == 1) {
				click_DelMenu();
			}

		} else
			System.out.println("행이 선택되지 않음");
	}

	// Side -> 결제 버튼 이벤트
	private void click_Payment() {
		if (tblOrder.getRowCount() != 0) {
			mf.changePanel(new PayPanel(mf, tblOrder));
		}
	}
}