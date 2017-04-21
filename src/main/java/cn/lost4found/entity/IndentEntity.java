package cn.lost4found.entity;

import java.io.Serializable;
import java.util.Date;

public class IndentEntity implements Serializable {

	private static final long serialVersionUID = -4628592518989354944L;
	
	private String id;
	private String bookId;
	private String userId;
	private Date time;
	private String addressId;

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
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
