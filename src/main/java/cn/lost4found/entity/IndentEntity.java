package cn.lost4found.entity;

import java.io.Serializable;
import java.util.Date;

public class IndentEntity implements Serializable {

	private static final long serialVersionUID = -4628592518989354944L;

	private String id;
	private String bookId;
	private String userId;
	private String receiverName;
	private String receiverTel;
	private String address;
	/*
	 * status 订单的状态 0 用户删除订单，已从用户的查看列表去除。 1 刚生成，待支付 2 已支付，待发货 3 已发货，待签收 4已签收，待评论
	 * 5 已评论，订单完成
	 */
	private int status;
	private int commentLevel;
	private String commentContent;
	private Date generatTime;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Date getGeneratTime() {
		return generatTime;
	}

	public void setGeneratTime(Date generatTime) {
		this.generatTime = generatTime;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	@Override
	public String toString() {
		return "IndentEntity [id=" + id + ", bookId=" + bookId + ", userId=" + userId + ", receiverName=" + receiverName
				+ ", receiverTel=" + receiverTel + ", address=" + address + ", status=" + status + ", commentLevel="
				+ commentLevel + ", commentContent=" + commentContent + ", generatTime=" + generatTime
				+ ", commentTime=" + commentTime + "]";
	}

}
