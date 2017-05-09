package cn.lost4found.entity;

import java.io.Serializable;
import java.util.Date;

public class CartEntity implements Serializable {

	private static final long serialVersionUID = 7241584596910913704L;
	private String id;
	private String userId;
	private String bookId;
	private Date addTime;
	private String purchaseId;
	private Date purchaseTime;
	private String indentId;

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

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Date getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public String getIndentId() {
		return indentId;
	}

	public void setIndentId(String indentId) {
		this.indentId = indentId;
	}

	@Override
	public String toString() {
		return "CartEntity [id=" + id + ", userId=" + userId + ", bookId=" + bookId + ", addTime=" + addTime
				+ ", purchaseId=" + purchaseId + ", purchaseTime=" + purchaseTime + ", indentId=" + indentId + "]";
	}

}
