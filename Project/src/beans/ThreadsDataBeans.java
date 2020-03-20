package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadsDataBeans {

	private int id;
	private String userName;
	private String title;
	private int threadsCategoryId;
	private int categoryId;
	private String categoryName;
	private Date createDate;
	private Date updatedate;
	private int userId;
	private String message;

	public ThreadsDataBeans(int id, String categoryName2, int categoryId2, String title2, Date createDate2) {
		this.id = id;
		this.categoryName = categoryName2;
		this.categoryId = categoryId2;
		this.title = title2;
		this.createDate = createDate2;
	}

	public ThreadsDataBeans(int id, String title2, String userName2, Date createDate2, int userId ) {

		this.id = id;
		this.title = title2;
		this.userName = userName2;
		this.createDate = createDate2;
		this.userId = userId;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getThreadsCategoryId() {
		return threadsCategoryId;
	}

	public void setThreadsCategoryId(int threadsCategoryId) {
		this.threadsCategoryId = threadsCategoryId;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFormatCreateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
		return sdf.format(createDate);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
