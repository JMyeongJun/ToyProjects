package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.CategoryDao;
import model.CategoryVo;
import model.MenuDao;
import model.MenuVo;

public class MenuPanel extends BasicPanel {

	JButton btnAddMenu, btnAddCat;

	Vector<CategoryVo> cat;
	Vector<MenuVo> menu;
	MenuDao menuDao;
	CategoryDao cateDao;
	JTable jTable;
	JScrollPane pane;
	AddMenu adm = null;
	AddCat adc = null;
	ClickedMenu ckm = null;
	Vector<JButton> catButton;
	Vector<JButton> menuButton;
	JScrollPane jsc;

	public MenuPanel() {
		initComponent();
		bottom.revalidate();
		bottom.repaint();

	}

	private void initComponent() {

		// top_bottom Layout 설정
		top_bottom.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		top_bottom.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		jsc = new JScrollPane(top_bottom);

		top.remove(top_bottom);
		top.add(jsc);

		// side 패널
		side.setLayout(new BorderLayout());
		// bottom 패널
		bottom.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		bottom.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

		btnAddCat = new JButton("+카테고리");
		btnAddMenu = new JButton("메뉴 등록");

		CategoryDao dao = new CategoryDao();
		cat = dao.getCategoryList();
		catButton = new Vector<JButton>();
		for (CategoryVo vo : cat) {
			catButton.add(new JButton(vo.getCategoryName()));
		}
		for (JButton jbtn : catButton) {
			jbtn.addActionListener(new CategoryAction());
			top_bottom.add(jbtn);
		}

		MenuDao mdao = new MenuDao();
		menu = mdao.getMenuList();
		menuButton = new Vector<JButton>();
		for (MenuVo vo : menu) {
			menuButton.add(new JButton(vo.getMenuName() +" "+ vo.getPrice()));
		}
		for (JButton mbtn : menuButton) {
			mbtn.addActionListener(new MenuAction());
			bottom.add(mbtn);
		}

		btnAddCat.setPreferredSize(new Dimension(100, 50));
		btnAddMenu.setPreferredSize(new Dimension(100, 50));

		side.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

		bottom.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		top_bottom.add(btnAddCat);

		side.add(btnAddMenu, BorderLayout.SOUTH);

		this.btnAddMenu.addActionListener((e) -> {
			if (e.getSource() == btnAddMenu) {
				if (adm != null) {
					adm.dispose();
				}
				adm = new AddMenu(this);
			}
		});
		this.btnAddCat.addActionListener((e) -> {
			if (e.getSource() == btnAddCat) {
				if (adc != null) {
					adc.dispose();
				}
				adc = new AddCat(this);
			}
		});

		// this.btnCat1.addActionListener( this );
		// 카테고리 버튼 클릭시 이벤트 연결
		// this.

	}

	// datalist 처리 화면에 category들 보이게.
	private static Vector<CategoryVo> getDataList() {
		Vector<CategoryVo> v = new Vector<CategoryVo>();
		CategoryDao dao = new CategoryDao();
		v = dao.getCategoryList();
		return v;
	}

	private static Vector<MenuVo> getDataMenuList() {
		Vector<MenuVo> v = new Vector<MenuVo>();
		MenuDao dao = new MenuDao();
		v = dao.getMenuList();
		return v;
	}

	public static void main(String[] args) {
		new MenuPanel();

	}

	// 새로고침

	class CategoryAction implements ActionListener {
		CategoryDao cdao = new CategoryDao();
		String msg = "";

		@Override
		public void actionPerformed(ActionEvent e) {
			String catName = e.getActionCommand();
			int choice = JOptionPane.showConfirmDialog(null, catName + "을(를) 삭제하시겠습니까?", "삭제확인",
					JOptionPane.YES_NO_OPTION);
			System.out.println(choice);
			// 예:0 아니오:1 x: -1
			if (choice == 0) {
				cdao.deleteCategory(catName);

			}
			if (choice == 1) {
				msg = catName + "(이)가 삭제되지 않았습니다.";
			}

		}

	}

	class MenuAction implements ActionListener {
		MenuDao mdao = new MenuDao();
		String msg = "";

		@Override
		public void actionPerformed(ActionEvent e) {
			if (ckm != null) {
				ckm.dispose();
			}
			String[] a = e.getActionCommand().split(" ");
			System.out.println(e.getActionCommand());
			ckm = new ClickedMenu(a[0]);

		}
	}

}
