package cn.lost4found.dao;

import java.util.LinkedList;

import cn.lost4found.entity.BookEntity;

public interface BookDao extends BaseDao<BookEntity> {

	public LinkedList<String> queryByNameDescAuthorPressLimited(String keyword, int index, int limit) throws Exception;
	
	public LinkedList<String> queryOrderLimitByArgs(String field, String index, String limit);
	
	public LinkedList<String> queryOrderDescLimitByArgs(String field, int index, int limit);
	
}
