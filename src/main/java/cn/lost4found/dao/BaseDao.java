package cn.lost4found.dao;

import org.apache.ibatis.annotations.Param;

public interface BaseDao<T> {
	/**
	 * 添加一条记录
	 * @param value
	 * @throws Exception
	 */
	public void insert(T value) throws Exception;
	
	public void delete(@Param("uf")String uniqField, @Param("value")Object value) throws Exception;
	
	public T select(@Param("uf")String uniqField, @Param("value")Object value) throws Exception;
	
	public void update(T value) throws Exception;
	
}
