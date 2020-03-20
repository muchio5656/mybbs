package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostsDataBeans {

	private int id;
	private String message;
	private String userName;
	private String title;
	private Date createDate;
	private Date updateDate;
	private int threadId;
	private int userId;

	public PostsDataBeans(int id2, String message2, String userName2, String title,Date createDate2,
			int userId,int threadId) {

		this.id = id2;
		this.message = message2;
		this.userName = userName2;
		this.userId = userId;
		this.title = title;
		this.createDate = createDate2;
		this.threadId = threadId;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
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

}
