package cn.lost4found.dao;

import java.util.LinkedList;
import org.apache.ibatis.annotations.Param;
import cn.lost4found.entity.FavoriteEntity;

public interface FavoriteDao extends BaseDao<FavoriteEntity> {
	
	public FavoriteEntity selectByBookIdAndUserId(String bookId, String userId) throws Exception;
	
	public void deleteByBookIdAndUserId(String bookId, String userId) throws Exception;
	
	public LinkedList<String> queryFavoriteBookIdByUserIdLimited(
			@Param("userId")String userId, @Param("index")int index, @Param("limit")int limit 
			) throws Exception;
	
}
