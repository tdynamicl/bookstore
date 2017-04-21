package cn.lost4found.dao;

import java.util.LinkedList;

import cn.lost4found.entity.RefBookKeywordEntity;

public interface RefBookKeywordDao extends BaseDao<RefBookKeywordEntity> {

	/**
	 * 根据关键字查询
	 * @param keywordId 关键字id
	 * @param index 第index个
	 * @param limit 查询limit个 
	 * @throws Exception
	 */
	public LinkedList<String> queryByKeywordIdLimited(String keywordId, int index, int limit) throws Exception;
	
}
