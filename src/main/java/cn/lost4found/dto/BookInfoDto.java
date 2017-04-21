package cn.lost4found.dto;

import java.text.SimpleDateFormat;

public class BookInfoDto {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String id;
	private String name;
	private String desc;
	private String authorName;
	private String pressName;
	private String pressTime;
	private int rankTotal;
	private String rankLevel;
	private String price;

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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPressName() {
		return pressName;
	}

	public void setPressName(String pressName) {
		this.pressName = pressName;
	}

	public String getPressTime() {
		return pressTime;
	}

	public void setPressTime(String pressTime) {
		this.pressTime = pressTime;
	}

	public int getRankTotal() {
		return rankTotal;
	}

	public void setRankTotal(int rankTotal) {
		this.rankTotal = rankTotal;
	}

	public String getRankLevel() {
		return rankLevel;
	}

	public void setRankLevel(String rankLevel) {
		this.rankLevel = rankLevel;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BookInfoDto [id=" + id + ", name=" + name + ", desc=" + desc + ", authorName=" + authorName
				+ ", pressName=" + pressName + ", pressTime=" + pressTime + ", rankTotal=" + rankTotal + ", rankLevel="
				+ rankLevel + ", price=" + price + "]";
	}

	
	
}
