package model;

public class DaoTest {
	public static void main(String[] args) {
		
		MenuDao dao = new MenuDao();
		
		dao.deleteMenu("아아");
		
		for(MenuVo vo : dao.getMenuList()) {
			CategoryDao dao2 = new CategoryDao();
			System.out.print(vo.toString());
			System.out.println(dao2.getCategoryById(vo.getCategoryID()).getCategoryName());
			
		}
		
	}
}
