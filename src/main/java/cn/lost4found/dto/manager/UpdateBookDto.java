package cn.lost4found.dto.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class UpdateBookDto {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String id;
	private String name;
	private String description;
	private String author;
	private String press;
	private String pressTimeString;
	private Date pressTime;
	private int price;
	private String addTimeString;
	private Date addTime;
	private int[] keywords;
	
	public String getPriceStr(){
		return (((double)this.price)/100)+"";
	}
	public static SimpleDateFormat getSdf() {
		return sdf;
	}
	public static void setSdf(SimpleDateFormat sdf) {
		UpdateBookDto.sdf = sdf;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Date getPressTime() throws ParseException {
		return sdf.parse(pressTimeString);
	}
	public void setPressTime(Date pressTime) {
		this.pressTime = pressTime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAddTimeString() {
		return addTimeString;
	}
	public void setAddTimeString(String addTimeString) {
		this.addTimeString = addTimeString;
	}
	public Date getAddTime() throws ParseException {
		return sdf.parse(addTimeString);
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int[] getKeywords() {
		return keywords;
	}
	public void setKeywords(int[] keywords) {
		this.keywords = keywords;
	}
	@Override
	public String toString() {
		return "UpdateBookDto [id=" + id + ", name=" + name + ", description="
				+ description + ", author=" + author + ", press=" + press
				+ ", pressTimeString=" + pressTimeString + ", pressTime="
				+ pressTime + ", price=" + price + ", addTimeString="
				+ addTimeString + ", addTime=" + addTime + ", keywords="
				+ Arrays.toString(keywords) + "]";
	}
}
