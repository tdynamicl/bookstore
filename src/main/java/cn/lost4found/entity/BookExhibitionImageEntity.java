package cn.lost4found.entity;

import java.io.Serializable;

public class BookExhibitionImageEntity implements Serializable {
	private static final long serialVersionUID = 3443999928661238956L;

	private String bookId;
	private String imageStream;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getImageStream() {
		return imageStream;
	}

	public void setImageStream(String imageStream) {
		this.imageStream = imageStream;
	}

}
