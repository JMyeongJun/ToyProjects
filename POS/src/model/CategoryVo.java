package model;

public class CategoryVo {

	private int categoryId;
	private String categoryName;

	public CategoryVo() {
	}

	public CategoryVo(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public int getCategoryID() {
		return categoryId;
	}

	public void setCategoryID(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "CategoryVo [categoryID=" + categoryId + ", categoryName=" + categoryName + "]";
	}

}
