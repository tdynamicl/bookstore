package cn.lost4found.dao;

import java.util.LinkedList;

import cn.lost4found.entity.BookExhibitionImageEntity;

public interface BookExhibitionImageDao extends BaseDao<BookExhibitionImageEntity> {

	public LinkedList<BookExhibitionImageEntity> selectAll() throws Exception;
	
}
