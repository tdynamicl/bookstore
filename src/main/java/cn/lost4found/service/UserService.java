package cn.lost4found.service;

import java.util.LinkedList;
import cn.lost4found.dto.BookInfoDto;
import cn.lost4found.dto.IndentDto;
import cn.lost4found.dto.SubmitIndentDto;
import cn.lost4found.dto.UserRegisterDto;
import cn.lost4found.entity.IndentEntity;
import cn.lost4found.entity.UserEntity;

public interface UserService {
	
	/**
	 * 用户登录
	 * @param account 用户名
	 * @param password 密码
	 * @return 返回登录信息
	 * @throws Exception
	 */
	public UserEntity login(String account, String password) throws Exception;
	
	/**
	 * 用户注册
	 * @param userDto 包含各种注册需要的信息
	 * @throws Exception
	 */
	public void register(UserRegisterDto userDto) throws Exception;
	
	/**
	 * 通过id获取书籍信息
	 * @param id
	 * @return BookInfoDto
	 * @throws Exception
	 */
	public BookInfoDto loadBookInfo(String id) throws Exception;
	
	/**
	 * 获得书籍图片的base64编码
	 * @param bookId
	 * @return
	 * @throws Exception
	 */
	public String loadBookImage(String bookId) throws Exception;
	
	/**
	 * 通过类型字符串和号来加载
	 * @param word 
	 * @param index 第index到index+10个
	 * @return LinkedList
	 * @throws Exception
	 */
	public LinkedList<BookInfoDto> loadBookInfosByCategory(String word, int index) throws Exception;

	/**
	 * 提交订单
	 * @param submitIndentDto
	 * @return 返回生成的订单id
	 * @throws Exception
	 */
	public String submitIndent(SubmitIndentDto submitIndentDto) throws Exception;
	
	/**
	 * 获得订单实体
	 * @param id
	 * @return 订单信息实体
	 * @throws Exception
	 */
	public IndentEntity loadIndentInfo(String id, String userId) throws Exception;
	
	/**
	 * 支付订单
	 * @param id
	 * @param userId
	 * @throws Exception
	 */
	public void purchaseIndent(String id, String userId) throws Exception;
	
	/**
	 * 取消订单
	 * @param id
	 * @param userId
	 * @throws Exception
	 */
	public void cancelIndent(String id, String userId) throws Exception;
	
	/**
	 * 签收订单
	 * @param id
	 * @param userId
	 * @throws Exception
	 */
	public void receivedIndent(String id, String userId) throws Exception;
	
	/**
	 * 评价订单
	 * @param id
	 * @param userId
	 * @param content
	 * @param level
	 * @throws Exception
	 */
	public void rateIndent(String id, String userId, String content, int level) throws Exception;
	
	/**
	 * 删除订单
	 * @param id
	 * @param userId
	 * @throws Exception
	 */
	public void deleteIndent(String id, String userId) throws Exception;
	
	/**
	 * 获取某用户未完成订单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public LinkedList<IndentDto> loadUnfinishedIndent(String userId) throws Exception;
	
	/**
	 * 获取某用户已完成订单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public LinkedList<IndentDto> loadFinishedIndent(String userId) throws Exception;

	/**
	 * 用户添加书籍收藏
	 * @param bookId
	 * @param userId
	 * @throws Exception
	 */
	public void addFavoriteBook(String bookId, String userId) throws Exception;

	/**
	 * 用户取消书籍收藏
	 * @param bookId
	 * @param userId
	 * @throws Exception
	 */
	public void delFavoriteBook(String bookId, String userId) throws Exception;
	
	/**
	 * 检查用户是否收藏此书
	 * @param bookId
	 * @param userId
	 * @throws Exception
	 */
	public boolean checkFavorite(String bookId, String userId) throws Exception;
	
	/**
	 * 模糊查询 从书名，书描述，作者名，出版社中查
	 * @param keyword
	 * @param index
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public LinkedList<BookInfoDto> queryByNameDescAuthorPressLimited(String keyword, int index, int limit) throws Exception; 
	
}
