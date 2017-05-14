package cn.lost4found.dao;

import org.apache.ibatis.annotations.Param;
import cn.lost4found.entity.CoverEntity;

public interface CoverDao extends BaseDao<CoverEntity> {
	
	public int exist(@Param("uf")String uniqField, @Param("value")Object value) throws Exception;
	
	public String selectByBookId(@Param("uf")String uniqField, @Param("value")Object value)throws Exception;
}
