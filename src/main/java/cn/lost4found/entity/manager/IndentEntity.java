package cn.lost4found.entity.manager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IndentEntity implements Serializable {

	private static final long serialVersionUID = -4628592518989354944L;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	private String id;
	private String bookId;
	private String bookName;
	private String userId;
	private String userName;
	private String receiverName;
	private String receiverTel;
	private String address;
	private int status;
	private int commentLevel;
	private String commentContent;
	private Date generateTime;
	private Date commentTime;

	
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

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverTel() {
		return receiverTel;
	}

	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public String getGenerateTimeStr() {
		return sdf.format(this.generateTime);
	}

	public String getCommentTimeStr() {
		if(this.commentTime!=null){
			return sdf.format(this.commentTime);
		}
		else{
			return "";
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "IndentEntity [id=" + id + ", bookId=" + bookId + ", bookName="
				+ bookName + ", userId=" + userId + ", userName=" + userName
				+ ", receiverName=" + receiverName + ", receiverTel="
				+ receiverTel + ", address=" + address + ", status=" + status
				+ ", commentLevel=" + commentLevel + ", commentContent="
				+ commentContent + ", generateTime=" + generateTime
				+ ", commentTime=" + commentTime + "]";
	}
	
}
