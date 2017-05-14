package cn.lost4found.service;

import cn.lost4found.dto.manager.AddBookDto;
import cn.lost4found.dto.manager.HintMessageDto;
import cn.lost4found.dto.manager.QueryBookDto;
import cn.lost4found.dto.manager.QueryIndentDto;
import cn.lost4found.dto.manager.UpdateBookDto;
import cn.lost4found.entity.manager.IndentEntity;
import cn.lost4found.entity.ManagerEntity;

public interface ManagerService {

	public HintMessageDto getHint()throws Exception;
	
	public ManagerEntity login(String account, String password)
			throws Exception;

	public void addBook(AddBookDto addBookDto) throws Exception;

	public void delBook(String id) throws Exception;

	public void updateBook(UpdateBookDto updateBookDto) throws Exception;

	public void queryBooks(QueryBookDto queryBookDto) throws Exception;

	public UpdateBookDto existBook(String id) throws Exception;
	
	public void uploadCover(String bookId,String cover)throws Exception;
	
	public String useCover(String bookId)throws Exception;
	
	public void queryIndents(QueryIndentDto queryIndentDto)throws Exception;
	
	public IndentEntity checkIndents(String id)throws Exception;
	
	public void changeIndent(String id,int status)throws Exception;
	
	public void deleteExBook(String id)throws Exception;
	
	public void changeExBookCover(String bookId,String image)throws Exception;
	
	public void pyBook(String oldId,String newId)throws Exception;
}
