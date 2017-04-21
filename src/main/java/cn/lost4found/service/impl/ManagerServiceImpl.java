package cn.lost4found.service.impl;

import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lost4found.common.MyException;
import cn.lost4found.dao.BookDao;
import cn.lost4found.dao.ManagerDao;
import cn.lost4found.dao.RefBookKeywordDao;
import cn.lost4found.dto.AddBookDto;
import cn.lost4found.entity.BookEntity;
import cn.lost4found.entity.ManagerEntity;
import cn.lost4found.entity.RefBookKeywordEntity;
import cn.lost4found.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private RefBookKeywordDao refBookKeywordDao;
	
	@Override
	public ManagerEntity login(String account, String password) throws Exception {
		if (account==null||account.trim().length()==0) {
			throw new MyException("用户名不能为空");
		}
		if (password==null||password.trim().length()==0) {
			throw new MyException("密码不能为空");
		}
		ManagerEntity managerEntity = managerDao.select("account", account);
		if (managerEntity==null) {
			throw new MyException("用户名不存在");
		}
		if (!password.equals(managerEntity.getPassword())) {
			throw new MyException("用户名或密码错误");
		}
		return managerEntity;
	}

	@Override
	public void addBook(AddBookDto addBookDto) throws Exception {
		String bookId = UUID.randomUUID().toString().replaceAll("-", "");
		BookEntity bookEntity = new BookEntity();
		RefBookKeywordEntity refBookKeywordEntity = new RefBookKeywordEntity();
		bookEntity.setId(bookId);
		bookEntity.setName(addBookDto.getName());
		bookEntity.setDescription(addBookDto.getDescription());
		bookEntity.setAuthor(addBookDto.getAuthor());
		bookEntity.setPress(addBookDto.getPress());
		bookEntity.setPrice(addBookDto.getPrice());
		bookEntity.setPressTime(AddBookDto.sdf.parse(addBookDto.getPressTimeString()));
		bookEntity.setAddTime(new Date(System.currentTimeMillis()));
		bookDao.insert(bookEntity);
		for (String k : addBookDto.getKeyword()) {
			refBookKeywordEntity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			refBookKeywordEntity.setKeywordId(k);
			refBookKeywordEntity.setBookId(bookId);
			refBookKeywordDao.insert(refBookKeywordEntity);
		}
	}

}
