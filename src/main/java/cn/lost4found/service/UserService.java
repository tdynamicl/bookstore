package cn.lost4found.service;

import java.util.LinkedList;
import cn.lost4found.dto.BookInfoDto;
import cn.lost4found.dto.SubmitIndentDto;
import cn.lost4found.dto.UserRegisterDto;
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
	public String submitIndent(SubmitIndentDto submitIndentDto)  throws Exception;
	
}