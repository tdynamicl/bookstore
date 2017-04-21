package cn.lost4found.entity;

import java.io.Serializable;
import java.util.Date;

public class CommentEnity implements Serializable {

	private static final long serialVersionUID = -1341526494495892698L;

	private String id;
	private String userId;
	private String bookId;
	private String level;
	private String content;
	private Date time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CommentEnity [id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", level=" + level
				+ ", content=" + content + ", time=" + time + "]";
	}

}
