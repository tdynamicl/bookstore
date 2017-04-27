package cn.lost4found.dao;

import cn.lost4found.entity.BookEntity;

public interface BookImageDao extends BaseDao<BookEntity> {
	
	public String selectOneByBookId(String bookId) throws Exception;
	
}
