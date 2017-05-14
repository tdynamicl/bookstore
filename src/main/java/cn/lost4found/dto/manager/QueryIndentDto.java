package cn.lost4found.dto.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import cn.lost4found.entity.manager.IndentEntity;

public class QueryIndentDto {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String bookName;
	private String userName;
	private String generateTimeStr;
	private Date generateTime;
	private int status;
	private int pageNo=1;
	private int pageSize=10;
	private LinkedList<IndentEntity> indents;
	private int totalRows;
	private int totalPages;
	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGenerateTimeStr() {
		return generateTimeStr;
	}

	public void setGenerateTimeStr(String generateTimeStr) {
		this.generateTimeStr = generateTimeStr;
	}

	public Date getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public LinkedList<IndentEntity> getIndents() {
		return indents;
	}

	public void setIndents(LinkedList<IndentEntity> indents) {
		this.indents = indents;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	@Override
	public String toString() {
		return "QueryIndentDto [bookName=" + bookName + ", userName="
				+ userName + ", generateTimeStr=" + generateTimeStr
				+ ", generateTime=" + generateTime + ", status=" + status
				+ ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", indents=" + indents + ", totalRows=" + totalRows
				+ ", totalPages=" + totalPages + "]";
	}

	public boolean isNotFound() {
		return this.indents == null || this.pageSize <= 0
				|| this.indents.size() == 0;
	}
	

}
