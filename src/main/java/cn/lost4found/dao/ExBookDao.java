package cn.lost4found.dao;

import java.util.LinkedList;

import org.apache.ibatis.annotations.Param;

import cn.lost4found.entity.ExBookEntity;

public interface ExBookDao extends BaseDao<ExBookEntity>{
	
	public void insertById(@Param("bookId")String bookId);
	
	public LinkedList<ExBookEntity> selectExBook();
	
	public void changeExBookCover(@Param("bookId")String bookId,@Param("image")String image);
}
