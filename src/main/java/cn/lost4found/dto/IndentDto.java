package cn.lost4found.dto;

import java.util.Date;

public class IndentDto {

	private String id;
	private Date generateTime;
	private String bookId;
	private String bookName;
	private int status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IndentDto [id=" + id + ", generateTime=" + generateTime + ", bookId=" + bookId + ", bookName="
				+ bookName + ", status=" + status + "]";
	}

}
