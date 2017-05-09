package cn.lost4found.entity;

import java.io.Serializable;

public class UserIconEntity implements Serializable {
	private static final long serialVersionUID = 1088035644199170040L;

	private String userId;
	private String userIcon;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	@Override
	public String toString() {
		return "UserIconEntity [userId=" + userId + ", userIcon=" + userIcon + "]";
	}

}
