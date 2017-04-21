package cn.lost4found.entity;

import java.io.Serializable;

public class BookKeywordRefEntity implements Serializable {

	private static final long serialVersionUID = 8586154041971015660L;

	private String id;
	private String bookId;
	private String keywordId;

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

	public String getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(String keywordId) {
		this.keywordId = keywordId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BookKeywordRefEntity [id=" + id + ", bookId=" + bookId + ", keywordId=" + keywordId + "]";
	}

}
