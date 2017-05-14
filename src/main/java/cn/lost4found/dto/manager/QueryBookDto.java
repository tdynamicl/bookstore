package cn.lost4found.dto.manager;

import java.util.LinkedList;

import cn.lost4found.entity.BookEntity;
import cn.lost4found.entity.ExBookEntity;

public class QueryBookDto {

	private String name;
	private int pageNo=1;
	private int pageSize=5;
	private LinkedList<BookEntity> books;
	private LinkedList<ExBookEntity> exBooks;
	private int totalRows;
	private int totalPages;
	
	public int getExSize(){
		return this.exBooks.size();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public LinkedList<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(LinkedList<BookEntity> books) {
		this.books = books;
	}

	public LinkedList<ExBookEntity> getExBooks() {
		return exBooks;
	}

	public void setExBooks(LinkedList<ExBookEntity> exBooks) {
		this.exBooks = exBooks;
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

	public boolean isNotFound() {
		return this.books == null || this.pageSize <= 0
				|| this.books.size() == 0;
	}
	
	public boolean isExNotFound() {
		return this.exBooks == null|| this.exBooks.size() == 0;
	}
	
	@Override
	public String toString() {
		return "QueryBookDto [name=" + name + ", pageNo=" + pageNo
				+ ", pageSize=" + pageSize + ", books=" + books + ", exBooks="
				+ exBooks + ", totalRows=" + totalRows + ", totalPages="
				+ totalPages + "]";
	}

}
