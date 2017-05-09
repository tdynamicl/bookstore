package cn.lost4found.common;

public class MyException extends Exception {
	private static final long serialVersionUID = -8951929412936353557L;
	private int errorCode = 1002;
	
	public MyException(String message) {
		super(message);
	}

	public MyException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
