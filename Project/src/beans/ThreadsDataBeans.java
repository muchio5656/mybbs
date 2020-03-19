package beans;

import java.util.Date;

public class ThreadsDataBeans {

	private int id;
	private String userName;
	private String title;
	private int threadsCategoryId;
	private Date createeDate;
	private Date updatedate;

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

	public Date getCreateeDate() {
		return createeDate;
	}

	public void setCreateeDate(Date createeDate) {
		this.createeDate = createeDate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

}
