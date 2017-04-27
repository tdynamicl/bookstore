package cn.lost4found.dao;

import cn.lost4found.entity.FavoriteEntity;

public interface FavoriteDao extends BaseDao<FavoriteEntity> {
	
	public FavoriteEntity selectByBookIdAndUserId(String bookId, String userId) throws Exception;
	
	public void deleteByBookIdAndUserId(String bookId, String userId) throws Exception;
	
}
