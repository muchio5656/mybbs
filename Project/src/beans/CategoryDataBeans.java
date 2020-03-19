package beans;

public class CategoryDataBeans {

	private int id;
	private String categoryName;
	private int threadCategoryId;
	private int threadId;
	private int categoryId;

	public CategoryDataBeans(int id2, String name) {

		this.id = id2;
		this.categoryName = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getThreadCategoryId() {
		return threadCategoryId;
	}

	public void setThreadCategoryId(int threadCategoryId) {
		this.threadCategoryId = threadCategoryId;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
