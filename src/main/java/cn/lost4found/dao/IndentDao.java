package cn.lost4found.dao;

import java.util.LinkedList;
import org.apache.ibatis.annotations.Param;
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
	
	/**
	 * 根据用户名，书名，下单日期查询所有订单
	 * @param bookName,userName,generateTime
	 * @return
	 * @throws Exception
	 * @author 杨贤伟
	 */
	public LinkedList<cn.lost4found.entity.manager.IndentEntity> selectByDto(@Param("bookName")String bookName,
			@Param("userName")String userName,
			@Param("generateTime")String generateTime,
			@Param("status")int status,
			@Param("first")int first,
			@Param("end")int end)throws Exception;
	/**
	 * 根据用户名，书名，下单日期查询总个数
	 * @param bookName,userName,generateTime
	 * @return
	 * @throws Exception
	 * @author 杨贤伟
	 */
	public int selectCountByDto(@Param("bookName")String bookName,
			@Param("userName")String userName,
			@Param("generateTime")String generateTime,
			@Param("status")int status,
			@Param("first")int first,
			@Param("end")int end)throws Exception;
	
	/**
	 * 根据id查询订单信息
	 * @author 杨贤伟
	 */
	public cn.lost4found.entity.manager.IndentEntity selectById(@Param("id")String id)throws Exception;
	
	/**
	 * 改变订单状态
	 * @author 杨贤伟
	 */
	public void changeIndentStatus(@Param("id")String id,@Param("status")int status)throws Exception;

	/**
	 * 通过订单状态查询订单
	 * @author 杨贤伟
	 */
	public void selectByStatus(@Param("status")String status,@Param("first")int first,
			@Param("end")int end)throws Exception;
	
}
