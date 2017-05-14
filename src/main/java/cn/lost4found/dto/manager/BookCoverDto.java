package cn.lost4found.dto.manager;

import org.springframework.web.multipart.MultipartFile;

public class BookCoverDto {
	private String bookId;
	private MultipartFile cover;
	private String coverName;
	private String coverType;
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public MultipartFile getCover() {
		return cover;
	}
	public void setCover(MultipartFile cover) {
		this.cover = cover;
	}
	public String getCoverName() {
		return coverName;
	}
	public void setCoverName(String coverName) {
		this.coverName = coverName;
	}
	public String getCoverType() {
		return coverType;
	}
	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}
	@Override
	public String toString() {
		return "BookCoverDto [bookId=" + bookId + ", cover=" + cover
				+ ", coverName=" + coverName + ", coverType=" + coverType + "]";
	}
	
}
