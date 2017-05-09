package cn.lost4found.dao;

import cn.lost4found.entity.UserIconEntity;

public interface UserIconDao extends BaseDao<UserIconEntity> {
	
	public String selectUserIconByUserId(String userId);

}
