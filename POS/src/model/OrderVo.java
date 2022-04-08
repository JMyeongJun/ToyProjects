package model;

public class OrderVo {
	private int menuId, queantity;
	private String menuName;

	public OrderVo(String menuName, int queantity) {
		super();
		this.menuName = menuName;
		this.queantity = queantity;
	}

	public OrderVo(int menuId, int queantity) {
		super();
		this.menuId = menuId;
		this.queantity = queantity;
	}

	public OrderVo(int menuId, String menuName, int queantity) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.queantity = queantity;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getQueantity() {
		return queantity;
	}

	public void setQueantity(int queantity) {
		this.queantity = queantity;
	}

	@Override
	public String toString() {
		return "OrderVo [menuId=" + menuId + ", queantity=" + queantity + "]";
	}

}
