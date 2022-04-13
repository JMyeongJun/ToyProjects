package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.CategoryDao;
import model.CategoryVo;
import model.MenuDao;
import model.MenuVo;

public class MenuPanel extends BasicPanel {
	MainFrame mf;
	MenuPanel mp;

	JButton btnAddMenu, btnAddCat;

	Vector<CategoryVo> catList;
	Vector<MenuVo> menuList;
	Vector<JButton> catButton;
	Vector<JButton> menuButton;
	JTable jTable;
	JScrollPane jsc, jsb;
	AddMenu adm = null;
	AddCat adc = null;
	ClickedMenu ckm = null;

	public MenuPanel() {
		initComponent();
	}

	public MenuPanel(MainFrame mf) {
		this();
		this.mf = mf;
		this.mp = this;
		btnPrev.addActionListener(e -> {
			mf.changePanel(new HomePanel(mf));
		});
	}

	private void initComponent() {
		subTitle.setText("메뉴 관리");
		
		// top_bottom Layout 설정
		top_bottom.setLayout(new GridLayout(1, 0, 10, 10));
		top_bottom.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		jsc = new JScrollPane(top_bottom);
		jsc.setBorder(BorderFactory.createEmptyBorder());
		
		top.remove(top_bottom);
		top.add(jsc);

		// side 패널
		side.setLayout(new BorderLayout());
		side.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));
		
		// bottom 패널
		bottom.setLayout(new GridLayout(0,5,10,10));
		bottom.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		

		btnAddCat = new JButton("+카테고리");
		btnAddCat.setBackground   (new Color(229, 239, 204));
		btnAddCat.setFont         (new Font("본고딕", Font.BOLD, 12));
		btnAddMenu = new JButton("메뉴 등록");
		btnAddMenu.setBackground(new Color(229, 239, 204));
		top_top.setBackground(Color.white);

		viewCategory();
		
		if (catList.size() != 0) {
			viewMenuByCategoryId(catList.get(0).getCategoryID());
		}

		top_bottom.add(btnAddCat);
		side.add(btnAddMenu, BorderLayout.SOUTH);

		this.btnAddMenu.addActionListener((e) -> {
			if (adm != null) {
				adm.dispose();
			}
			adm = new AddMenu(this);
		});
		this.btnAddCat.addActionListener((e) -> {
			if (adc != null) {
				adc.dispose();
			}
			adc = new AddCat(this);
		});
	}

	// 카테고리 버튼 생성
	public void viewCategory() {
		top_bottom.removeAll();

		CategoryDao dao = new CategoryDao();
		catList = dao.getCategoryList();
		catButton = new Vector<JButton>();
		for (CategoryVo vo : catList) {
			catButton.add(new JButton(vo.getCategoryName()));
		}
		for (JButton jbtn : catButton) {
			jbtn.addActionListener(new CategoryAction());
			jbtn.setBackground   (colorCategory);
			jbtn.setForeground   (Color.WHITE);
			jbtn.setFont         (basicFont(20, Font.BOLD));
			top_bottom.add(jbtn);
		}

		top_bottom.add(btnAddCat);
		
		top_bottom.revalidate();
		top_bottom.repaint();
	}
	
	// 메뉴 버튼 생성
	public void viewMenuByCategoryId(int categoryId) {
		bottom.removeAll();

		MenuDao mdao = new MenuDao();
		menuList = mdao.getMenuListByCategoryId(categoryId);

		menuButton = new Vector<JButton>();
		for (MenuVo vo : menuList) {
			menuButton.add(new JButton(vo.getMenuName()));
		}

		for (JButton mbtn : menuButton) {
			mbtn.setBackground   (colorMenu);
			mbtn.setForeground   (Color.WHITE);
			mbtn.setFont         (basicFont(20, Font.BOLD));
			mbtn.addActionListener(new MenuAction());
			bottom.add(mbtn);
		}
		spacePanel(bottom);
		bottom.revalidate();
		bottom.repaint();
	}
	
	public void spacePanel(JPanel panel) {
		for(int i = panel.getComponentCount(); i < 15; i++) {
			JPanel emptyPanel = new JPanel();
			emptyPanel.setBackground(colorBottom);
			panel.add(emptyPanel);
		}
	}

	class CategoryAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			CategoryDao cdao = new CategoryDao();
			CategoryVo cvo;
			cvo = cdao.getCategoryByName(e.getActionCommand());

			System.out.println(cvo);

			int choice = JOptionPane.showConfirmDialog(null, cvo.getCategoryName() + "을(를) 삭제하시겠습니까?", "삭제확인",
					JOptionPane.YES_NO_OPTION);
			System.out.println(choice);
			// 예:0 아니오:1 x: -1
			if (choice == 0) {
				MenuDao mdao = new MenuDao();
				if (mdao.getMenuListByCategoryId(cvo.getCategoryID()).size() > 0) {
					JOptionPane.showMessageDialog(null, "먼저 해당 카테고리 내 모든 메뉴를 삭제 해주십시오.", "에러",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				cdao.deleteCategory(cvo.getCategoryName());
				viewCategory();
			} else {
				System.out.println(cvo.getCategoryName() + "(이)가 삭제되지 않았습니다.");
				viewMenuByCategoryId(cvo.getCategoryID());
			}
		}
	}

	class MenuAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ckm != null) {
				ckm.dispose();
			}

			String[] btnTxt = e.getActionCommand().split(" ");
			System.out.println(e.getActionCommand());
			System.out.println(btnTxt[0]);
			ckm = new ClickedMenu(btnTxt[0], mp);
		}
	}
}