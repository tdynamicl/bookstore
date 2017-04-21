package cn.lost4found.service;

import cn.lost4found.dto.AddBookDto;
import cn.lost4found.entity.ManagerEntity;

public interface ManagerService {

	public ManagerEntity login(String account, String password) throws Exception;
	
	public void addBook(AddBookDto addBookDto) throws Exception;
	
}
