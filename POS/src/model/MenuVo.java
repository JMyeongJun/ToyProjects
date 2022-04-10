package model;

public class MenuVo {
	private int menuID, price, categoryId;
	private String menuName;

	public MenuVo() {
	}
	
	public MenuVo(int menuID, String menuName, int price, int categoryID) {
		super();
		this.menuID = menuID;
		this.price = price;
		this.categoryId = categoryID;
		this.menuName = menuName;
	}
	
	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCategoryID() {
		return categoryId;
	}

	public void setCategoryID(int categoryID) {
		this.categoryId = categoryID;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Override
	public String toString() {
		return "MenuVo [menuID=" + menuID+ ", menuName=" + menuName + ", price=" + price + ", categoryID=" + categoryId
				+ "]";
	}

}
