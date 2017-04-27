package cn.lost4found.entity;

import java.io.Serializable;
import java.util.Date;

public class FavoriteEntity implements Serializable {

	private static final long serialVersionUID = 7953086193476314356L;

	private String id;
	private String bookId;
	private String userId;
	private Date addTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "FavoriteEntity [id=" + id + ", bookId=" + bookId + ", userId=" + userId + ", addTime=" + addTime + "]";
	}

}
