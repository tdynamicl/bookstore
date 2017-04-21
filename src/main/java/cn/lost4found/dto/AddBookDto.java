package cn.lost4found.dto;

import java.text.SimpleDateFormat;
import java.util.Arrays;

public class AddBookDto {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String name;
	private String description;
	private String author;
	private String press;
	private String pressTimeString;
	private int price;
	private String[] keyword;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getPressTimeString() {
		return pressTimeString;
	}

	public void setPressTimeString(String pressTimeString) {
		this.pressTimeString = pressTimeString;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String[] getKeyword() {
		return keyword;
	}

	public void setKeyword(String[] keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "AddBookDto [name=" + name + ", description=" + description + ", author=" + author + ", press=" + press
				+ ", pressTimeString=" + pressTimeString + ", price=" + price + ", keyword=" + Arrays.toString(keyword)
				+ "]";
	}

}
