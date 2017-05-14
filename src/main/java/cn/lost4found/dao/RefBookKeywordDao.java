package cn.lost4found.dao;

import java.util.LinkedList;
import org.apache.ibatis.annotations.Param;
import cn.lost4found.entity.RefBookKeywordEntity;

public interface RefBookKeywordDao extends BaseDao<RefBookKeywordEntity> {

	public LinkedList<RefBookKeywordEntity> selectByBookId(
			@Param("uf") String uniqField, @Param("value") Object value)
			throws Exception;

	/**
	 * 根据关键字查询
	 * @param keywordId 关键字id
	 * @param index 第index个
	 * @param limit 查询limit个 
	 * @throws Exception
	 */
	public LinkedList<String> queryByKeywordIdLimited(String keywordId, int index, int limit) throws Exception;
	
	public int selectCountByClass(@Param("classify")String classify)throws Exception;
}
