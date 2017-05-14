package cn.lost4found.dto.manager;

public class HintMessageDto {
	
	private int sTwo;//未提交订单数量
	private int allBookNumber;//总书量
	private int eduBookNumber;//教育类图书数量
	private int novelBookNumber;//小说类图书数量
	private int artBookNumber;//文艺类图书数量
	private int childBookNumber;//儿童类小说数量
	private int liveBookNumber;//生活类小说数量
	private int scienceBookNumber;//科技类小说数量
	
	public int getsTwo() {
		return sTwo;
	}
	public void setsTwo(int sTwo) {
		this.sTwo = sTwo;
	}
	public int getAllBookNumber() {
		return allBookNumber;
	}
	public void setAllBookNumber(int allBookNumber) {
		this.allBookNumber = allBookNumber;
	}
	public int getEduBookNumber() {
		return eduBookNumber;
	}
	public void setEduBookNumber(int eduBookNumber) {
		this.eduBookNumber = eduBookNumber;
	}
	public int getNovelBookNumber() {
		return novelBookNumber;
	}
	public void setNovelBookNumber(int novelBookNumber) {
		this.novelBookNumber = novelBookNumber;
	}
	public int getArtBookNumber() {
		return artBookNumber;
	}
	public void setArtBookNumber(int artBookNumber) {
		this.artBookNumber = artBookNumber;
	}
	public int getChildBookNumber() {
		return childBookNumber;
	}
	public void setChildBookNumber(int childBookNumber) {
		this.childBookNumber = childBookNumber;
	}
	public int getLiveBookNumber() {
		return liveBookNumber;
	}
	public void setLiveBookNumber(int liveBookNumber) {
		this.liveBookNumber = liveBookNumber;
	}
	public int getScienceBookNumber() {
		return scienceBookNumber;
	}
	public void setScienceBookNumber(int scienceBookNumber) {
		this.scienceBookNumber = scienceBookNumber;
	}
	@Override
	public String toString() {
		return "HintMessageDto [sTwo=" + sTwo + ", allBookNumber="
				+ allBookNumber + ", eduBookNumber=" + eduBookNumber
				+ ", novelBookNumber=" + novelBookNumber + ", artBookNumber="
				+ artBookNumber + ", childBookNumber=" + childBookNumber
				+ ", liveBookNumber=" + liveBookNumber + ", scienceBookNumber="
				+ scienceBookNumber + "]";
	}
}
