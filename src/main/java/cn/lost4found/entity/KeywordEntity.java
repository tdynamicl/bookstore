package cn.lost4found.entity;

import java.io.Serializable;

public class KeywordEntity implements Serializable {

	private static final long serialVersionUID = 2392308410754342293L;

	private String id;
	private String keyword;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "KeywordEntity [id=" + id + ", keyword=" + keyword + "]";
	}

}
