package cn.lost4found.entity;

public class CoverEntity {
	
	private String id;
	private String bookId;
	private String imageString;

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

	public String getImageString() {
		return imageString;
	}

	public void setImageString(String imageString) {
		this.imageString = imageString;
	}

	@Override
	public String toString() {
		return "CoverEntity [id=" + id + ", bookId=" + bookId
				+ ", imageString=" + imageString + "]";
	}

}
