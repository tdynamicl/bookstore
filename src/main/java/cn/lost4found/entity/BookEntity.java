package cn.lost4found.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookEntity implements Serializable {

	private static final long serialVersionUID = -7956575569665873343L;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String id;
	private String name;
	private String description;
	private String author;
	private String press;
	private Date pressTime;
	private int price;
	private Date addTime;
	
	public String getPriceStr(){
		return (((double)this.price)/100)+"";
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

	public Date getPressTime() {
		return pressTime;
	}

	public String getPressTimeString() {
		return sdf.format(pressTime);
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

	public Date getAddTime() {
		return addTime;
	}

	public String getAddTimeString() {
		return sdf.format(addTime);
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "BookEntity [id=" + id + ", name=" + name + ", description="
				+ description + ", author=" + author + ", press=" + press
				+ ", pressTime=" + pressTime + ", price=" + price
				+ ", addTime=" + addTime + "]";
	}

}
