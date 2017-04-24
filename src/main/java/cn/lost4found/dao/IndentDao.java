package cn.lost4found.dao;

import cn.lost4found.entity.IndentEntity;

public interface IndentDao extends BaseDao<IndentEntity> {
	
	public Double queryAvgOfCommentLevelByBookId(String bookId) throws Exception;
	
	public Integer queryTotalOfCommentByBookId(String bookId) throws Exception;

}
