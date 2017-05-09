package cn.lost4found.dao;

import java.util.LinkedList;

import cn.lost4found.dto.CommentDto;
import cn.lost4found.entity.IndentEntity;

public interface IndentDao extends BaseDao<IndentEntity> {
	
	/**
	 * 根据书id查询平均评分
	 * @param bookId
	 * @return
	 * @throws Exception
	 */
	public Double queryAvgOfCommentLevelByBookId(String bookId) throws Exception;
	
	/**
	 * 根据书id查询评论条数
	 * @param bookId
	 * @return
	 * @throws Exception
	 */
	public Integer queryTotalOfCommentByBookId(String bookId) throws Exception;

	/**
	 * 根据用户id查询所有未完成的订单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public LinkedList<IndentEntity> queryAllUnfinishedIndentByUserId(String userId) throws Exception;
	
	/**
	 * 根据用户id查询所有已完成的订单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public LinkedList<IndentEntity> queryAllFinishedIndentByUserId(String userId) throws Exception;
	
	/**
	 * 查询评论
	 * @param bookId
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public LinkedList<CommentDto> queryCommentsLimitByBookId(String bookId, int index) throws Exception;
	
}
