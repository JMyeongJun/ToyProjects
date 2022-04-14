package model;

public class OrderListVo {
	String menuName;
	int quantity, total_price;

	public OrderListVo(String menuName, int quantity, int total_price) {
		super();
		this.menuName = menuName;
		this.quantity = quantity;
		this.total_price = total_price;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	@Override
	public String toString() {
		return "OrderListVo [menuName=" + menuName + ", quantity=" + quantity + ", total_price=" + total_price + "]";
	}

}
