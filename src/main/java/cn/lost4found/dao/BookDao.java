package cn.lost4found.dao;

import java.util.LinkedList;
import org.apache.ibatis.annotations.Param;
import cn.lost4found.entity.BookEntity;

public interface BookDao extends BaseDao<BookEntity> {

	public LinkedList<String> queryByNameDescAuthorPressLimited(String keyword, int index, int limit) throws Exception;
	
	public LinkedList<String> queryOrderLimitByArgs(String field, String index, String limit);
	
	public LinkedList<String> queryOrderDescLimitByArgs(String field, int index, int limit);
	
	public LinkedList<BookEntity> selectByKey(@Param("key")String key,@Param("first")int first,@Param("end")int end);
	
	public int selectCountByKey(@Param("key")String key,@Param("first")int first,@Param("end")int end);
	
	public int selectTotalRows(@Param("key")String key);
	
}
