package view;

import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
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
	MainFrame mf;
// Field
	private GridBagLayout gb;
	private GridBagConstraints gbc;

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

	private void initComponent() {
//레이아웃 설정-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
//top_bottom 설정
		gb = new GridBagLayout();
		gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		top_bottom.setLayout(gb);

		bottom.setLayout(gb); // 이걸 그리드백으로 할거였으면 gbAdd(bottom_cate1,0,0,1,1)도 해줬어야 했다.
		viewBottom(); // bottom 패널
//		JButton btn_Cate2_Menu9 = new JButton("<html><body><center>카테2 메뉴9<br> <br>2500</center></body></html>");   // 버튼 내에서 줄띄우는 방법

		side.setLayout(gb);
		viewBasic();
		viewSide(); // side패널

		subTitle.setFont(new Font("본고딕", Font.BOLD, 50));
		btnPrev.setBackground(new Color(230, 230, 230));
//		Color btnCate_col = new Color(52, 152, 219);
//		Color btn_menu_col = new Color(94, 94, 94);
	}

//side버튼 기능연결 -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void actionPerformed(ActionEvent e) { // dao에 클로즈 넣었습니다

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

//그리드백 설정-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void gbAdd(JComponent c, int x, int y, int w, int h) {

		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
	}

// side -> 삭제 버튼--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void click_DelMenu() {

		int row = tblOrder.getSelectedRow(); // 클릭한 행 번호를 가져온다
		DefaultTableModel model = ((DefaultTableModel) tblOrder.getModel()); // 테이블 모델 설정해주고

		if (row >= 0) { // 행이 선택되어야만

			model.removeRow(row); // 선택된 행을 지움

		} else
			System.out.println("행이 선택되지 않음");
	}

// side -> plus 버튼--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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

// side -> minus 버튼--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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

			if (pre_Quantity == 1) { // 1에서 - 누르면 사라지게 하기
				click_DelMenu();
			}

		} else
			System.out.println("행이 선택되지 않음");
	}

// side -> payment 버튼 클릭 이벤트--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void click_Payment() {
		if (tblOrder.getRowCount() != 0) {
			mf.changePanel(new PayPanel(mf, tblOrder));
		}
	}

// bottom 패널 초기화면 --------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void viewBasic() {

		CategoryDao cdao = new CategoryDao();
		Vector<CategoryVo> vo = cdao.getCategoryList();
		MenuDao mdao = new MenuDao();
		Vector<MenuVo> mvoList = null;

		if (vo.size() != 0) {
			mvoList = mdao.getMenuListByCategoryId(vo.get(0).getCategoryID()); // vo 인덱스 0번째의 카테고리에 속한 메뉴들을
			btnMenu = new Vector<JButton>(); // 버튼배열 만들고
			for (MenuVo mvo : mvoList) { // 메뉴들 속성을 mvo에 넣음
				btnMenu.add(new JButton(mvo.getMenuName())); // 이름을 꺼내서 버튼이름으로 지정
			}

			int i = 0;
			gbc.insets = new Insets(5, 5, 5, 5);
			for (JButton btnM : btnMenu) { // 버튼배열들을 버튼으로 다시 지정해줌?
				gbAdd(btnM, (i % 4), (i / 4), 1, 1);
				btnM.addActionListener(new click_Menu()); // 버튼들 눌렀을때
				bottom.add(btnM); // 버튼M을 바텀패널에 추가함
				i++;
			}
		}
	}

// top_bottom 패널, bottom 패널-------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
	private void viewBottom() {

		btnCategory = new Vector<JButton>(); // JButton 타입의 btnCategory 벡터를 만들겠다.
		CategoryDao cdao = new CategoryDao(); // 카테고리 dao를 쓰겠다.

		System.out.println("카테고리 다오: " + cdao.getCategoryList());
		gbc.insets = new Insets(5, 5, 5, 5);
		for (CategoryVo cvo : cdao.getCategoryList()) { // 카테고리 VO에 GetcategoryList 배열의 0번째 index부터 ~ 없을때까지 넣겠다. [1,커피]
														// [2, 쥬스] [3, 에이드]
			btnCategory.add(new JButton(cvo.getCategoryName())); // btncategory버튼에 cvo에서 name을 불러와서 넣겠다 (반복, 카테고리 dao의
																	// getCategoryList가 없을때까지).[커피], [쥬스], [에이드]
			System.out.println("버튼이름 : " + cvo.getCategoryName());
		}
		;
		int j = 0;
		for (JButton btn : btnCategory) { // 버튼 btn에 btn카테고리를 0번째 index부터 없을때까지 넣겠다. -> 버튼 3개 생성 [커피', [쥬스], [에이드],
											// 버튼배열을 바로 패널위에 올릴 수 없기 때문에 한개한개 다시 만들어서 넣어준다.
			btn.addActionListener(e -> { // btn이 클릭되면
				btnMenu = new Vector<JButton>(); // btnmune라는 J버튼을 만든다

				bottom.removeAll(); // 바텀 패널을 전부 지우고

				System.out.println(e.getActionCommand());
				MenuDao mdao = new MenuDao(); // menu dao를 쓰겠다
				Vector<MenuVo> mvoList = mdao
						.getMenuListByCategoryId(cdao.getCategoryByName(e.getActionCommand()).getCategoryID());

				for (MenuVo mvo : mvoList) { // mvo에 다시 mvoList를 넣는다
					btnMenu.add(new JButton(mvo.getMenuName())); // mvo에서 메뉴이름만 가져와서 버튼 이름에 넣고 생성한다.
				}

				int i = 0;
				gbc.insets = new Insets(5, 5, 5, 5);
				for (JButton btnM : btnMenu) { // 버튼메뉴들을 버튼에 넣는다.
					btnM.addActionListener(new click_Menu()); // 버튼메뉴가 클릭되면 -> 클래스 만들어서 하는방법
					gbAdd(btnM, (i % 4), (i / 4), 1, 1);
					bottom.add(btnM); // 메뉴버튼들을 바텀 패널에 추가
					i++;
				}

				bottom.revalidate(); // 새로고침하고
				bottom.repaint(); // 자바에서 동적 변화 후에는 revalidate(재확인) 와 repaint가 항상 따라다닌다 (잔상이 보여서 새로그려야함)
			});
			gbAdd(btn, j, 0, 1, 1);
			top_bottom.add(btn); // 카테고리 버튼추가
			j++;
		}
	}

// side 패널-------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
	private void viewSide() {

		String[] tbl_Column = { "메뉴", "수량", "가격" };
		tblOrder = new JTable();

		tblOrder.setModel(new DefaultTableModel(null, tbl_Column) {
			public boolean isCellEditable(int row, int column) {
				return false; // 전체편집불가능
			}
		});
		DefaultTableModel model = (DefaultTableModel) tblOrder.getModel(); // 테이블 모델을 얻어온다
		JScrollPane scroll = new JScrollPane(tblOrder); // 스크롤도 컨테이너다 (스크롤 안에 테이블을 넣은거임)

		DefaultTableCellRenderer center = new DefaultTableCellRenderer(); // 가운데정렬
		center.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel center2 = tblOrder.getColumnModel();
		for (int i = 0; i < center2.getColumnCount(); i++) {
			center2.getColumn(i).setCellRenderer(center);
		}

		tblOrder.getParent().setBackground(Color.WHITE); // 테이블 색
		tblOrder.setShowVerticalLines(false); // 테이블 선 지우기
		tblOrder.setShowHorizontalLines(false);

		// side 나머지 설정
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

		lblOrder.setFont(new Font("본고딕", Font.BOLD, 40));

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

// bottom -> menu 버튼 클릭 이벤트--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	class click_Menu implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e2) {

			MenuDao dao = new MenuDao();
			String btn_MenuName = e2.getActionCommand();
			DefaultTableModel model = ((DefaultTableModel) tblOrder.getModel());

			for (MenuVo vo : dao.getMenuList()) { // vo에 모든 정보를 담음
				if (btn_MenuName.equals(vo.getMenuName())) { // 누른버튼의 이름과 vo의 이름이 같으면 실행함

					if (model.getRowCount() == 0) { // 테이블에 데이터가 한줄도 없으면
						model.addRow(new Object[] { btn_MenuName, 1, vo.getPrice() }); // 누른거 1개 추가함
					} else {
						int dupl = 0; // 중복확인용 변수
						for (int j = 0; j < model.getRowCount(); j++) {
							if (tblOrder.getValueAt(j, 0).equals(btn_MenuName)) { // 이미 메뉴1이 들어가있다면

								int pre_Quantity = (int) tblOrder.getValueAt(j, 1); // 메뉴1의 수량을 가져와서
								tblOrder.setValueAt(pre_Quantity + 1, j, 1); // 기존 수량에 1을 더한다.
								int pre_Price = (int) tblOrder.getValueAt(j, 2); // 가격도 같은방법
								tblOrder.setValueAt(pre_Price + vo.getPrice(), j, 2);
							} else
								dupl++; // 메뉴 1이 없다면 dupl라는 변수에 1을 더한다
						}

						if (dupl == model.getRowCount()) { // 메뉴1이 없다면 (열을 쭉 검색해서 한번이라도 메뉴1이 보였다면 dupl은 행개수 - 1이 되었을것임)

							model.addRow(new Object[] { btn_MenuName, 1, vo.getPrice() }); // 메뉴1을 추가한다
						}
					}
				}
			}
		}
	}

// 테이블 클릭 이벤트-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
}