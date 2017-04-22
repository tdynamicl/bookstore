package cn.lost4found.dto;

public class SubmitIndentDto {
	private String userId;
	private String bookId;
	private String addr;
	private String receiverName;
	private String receiverTel;

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	@Override
	public String toString() {
		return "SubmitIndentDto [userId=" + userId + ", bookId=" + bookId + ", addr=" + addr + ", receiverName="
				+ receiverName + ", receiverTel=" + receiverTel + "]";
	}

}
