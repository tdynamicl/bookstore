package cn.lost4found.service;

import java.util.LinkedList;
import cn.lost4found.dto.BookInfoDto;
import cn.lost4found.dto.UserRegisterDto;
import cn.lost4found.entity.UserEntity;

public interface UserService {
	
	public UserEntity login(String account, String password) throws Exception;
	
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
	
}
