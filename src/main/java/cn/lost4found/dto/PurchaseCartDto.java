package cn.lost4found.dto;

public class PurchaseCartDto {

	private String userId;
	private String addr;
	private String receiverName;
	private String receiverTel;
	private String cartIds;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getCartIds() {
		return cartIds;
	}

	public void setCartIds(String cartIds) {
		this.cartIds = cartIds;
	}

}
