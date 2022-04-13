package view;

import java.util.Vector;

import javax.swing.JButton;

import model.CategoryDao;
import model.CategoryVo;
import model.MenuDao;
import model.MenuVo;

public class OrderPanelTest extends BasicPanel{
	
	Vector<JButton> btnCategory, btnMenu;

	public OrderPanelTest() {
		initcomponents();
	}
	
	private void initcomponents(){
		btnCategory = new Vector<JButton>();
		
		CategoryDao cdao = new CategoryDao();
		
		for(CategoryVo cvo : cdao.getCategoryList()) {
			btnCategory.add(new JButton(cvo.getCategoryName()));
		}
		
		for(JButton btn : btnCategory) {
			btn.addActionListener(e -> {
				btnMenu = new Vector<JButton>();
			
				bottom.removeAll();
				
				System.out.println(e.getActionCommand());
				MenuDao mdao = new MenuDao();
				Vector<MenuVo> mvoList = mdao.getMenuListByCategoryId(cdao.getCategoryByName(e.getActionCommand()).getCategoryID());
				
				for(MenuVo mvo : mvoList) {
					btnMenu.add(new JButton(mvo.getMenuName()));
				}
				
				for(JButton btnM : btnMenu) {
					btnM.addActionListener(e2 -> {
						System.out.println(e2.getActionCommand());
						System.out.println(mdao.getMenuByName(e2.getActionCommand()).toString());
					});
					bottom.add(btnM);
				}
				
				bottom.revalidate();
				bottom.repaint();
			});
			top_bottom.add(btn);
		}
	}
	
}
