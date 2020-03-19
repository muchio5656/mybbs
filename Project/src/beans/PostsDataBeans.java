package beans;

import java.util.Date;

public class PostsDataBeans {

	private int id;
	private String message;
	private String userName;
	private String threadId;
	private Date createDate;
	private Date updateDate;

	public PostsDataBeans(int id2, String message2, String userName2, String threadid, java.sql.Date createDate2) {

		this.id = id2;
		this.message = message2;
		this.userName = userName2;
		this.threadId = threadid;
		this.createDate = createDate2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
