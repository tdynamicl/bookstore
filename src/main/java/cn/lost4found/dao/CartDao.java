package cn.lost4found.dao;

import java.util.LinkedList;

import cn.lost4found.entity.CartEntity;

public interface CartDao extends BaseDao<CartEntity> {
	
	public LinkedList<CartEntity> queryPurchaseIdIsNull(String userId) throws Exception;

	public void updatePurchaseInfo(CartEntity cartEntity) throws Exception;
	
}
