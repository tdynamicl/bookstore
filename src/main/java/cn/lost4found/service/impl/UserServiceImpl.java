package cn.lost4found.service.impl;

import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lost4found.dao.BookDao;
import cn.lost4found.dao.IndentDao;
import cn.lost4found.dao.KeywordDao;
import cn.lost4found.dao.RefBookKeywordDao;
import cn.lost4found.dao.UserDao;
import cn.lost4found.dto.BookInfoDto;
import cn.lost4found.dto.SubmitIndentDto;
import cn.lost4found.dto.UserRegisterDto;
import cn.lost4found.entity.BookEntity;
import cn.lost4found.entity.IndentEntity;
import cn.lost4found.entity.KeywordEntity;
import cn.lost4found.entity.UserEntity;
import cn.lost4found.common.MyException;
import cn.lost4found.common.Util;
import cn.lost4found.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private KeywordDao keywordDao;
	@Autowired
	private RefBookKeywordDao refBookKeywordDao;
	@Autowired
	private IndentDao indentDao;
	
	@Override
	public UserEntity login(String account, String password) throws Exception {
		if (account==null||account.length()!=32) {
			throw new MyException("用户名不能为空");
		}
		if (password==null||password.length()!=32) {
			throw new MyException("密码不能为空");
		}
		UserEntity userEntity = userDao.select("account", account);
		if (userEntity==null) {
			throw new MyException("用户名不存在");
		}
		if (!password.equals(userEntity.getPassword())) {
			throw new MyException("用户名或密码错误");
		}
		userEntity.setAccount("");
		userEntity.setPassword("");
		return userEntity;
	}

	@Override
	public void register(UserRegisterDto userDto) throws Exception {
		if (userDao.select("account", userDto.getAccount())!=null) {
			throw new MyException("该用户名已被注册");
		}
		if (userDto.getAccount()==null || userDto.getAccount().length()!=32) {
			throw new MyException("用户名不能为空");
		}
		if (userDto.getPassword()==null || userDto.getPassword().length()!=32) {
			throw new MyException("密码不能为空");
		}
		if (userDto.getNickname()==null || userDto.getNickname().trim().length()==0 || userDto.getNickname().trim().length()>20) {
			throw new MyException("昵称长度在1-20位之间");
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setId(Util.generateUUID());
		userEntity.setAccount(userDto.getAccount());
		userEntity.setPassword(userDto.getPassword());
		userEntity.setNickname(userDto.getNickname().trim());
		userEntity.setTelephone(userDto.getTelephone());
		userEntity.setEmail(userDto.getEmail());
		userDao.insert(userEntity);
	}
	
	@Override
	public BookInfoDto loadBookInfo(String id) throws Exception {
		BookEntity bookEntity = bookDao.select("id", id);
		BookInfoDto bookInfoDto = new BookInfoDto();
		if (bookEntity == null) {
			throw new MyException("这个书籍找不到了");
		}
		bookInfoDto.setId(bookEntity.getId());
		bookInfoDto.setName(bookEntity.getName());
		bookInfoDto.setDesc(bookEntity.getDescription());
		bookInfoDto.setPressTime(BookInfoDto.sdf.format(bookEntity.getPressTime()));
		bookInfoDto.setPrice(String.format("%.2f", bookEntity.getPrice()/100.0));
		bookInfoDto.setAuthorName(bookEntity.getAuthor());
		bookInfoDto.setPressName(bookEntity.getPress());
		//TODO 给bookInfoDto加入评分等信息
		bookInfoDto.setRankTotal(15);
		bookInfoDto.setRankLevel("4.3");
		return bookInfoDto;
	}

	@Override
	public LinkedList<BookInfoDto> loadBookInfosByCategory(String word, int index) throws Exception {
		KeywordEntity keywordEntity = keywordDao.select("keyword", word);
		if (keywordEntity==null) {
			throw new MyException("没有此图书分类");
		}
		LinkedList<String> bookIds = refBookKeywordDao.queryByKeywordIdLimited(keywordEntity.getId(), index, 10);
		if (bookIds.isEmpty()) {
			throw new MyException("暂无该分类的图书");
		}
		LinkedList<BookInfoDto> list = new LinkedList<>();
		for(String id : bookIds) {
			list.add(loadBookInfo(id));
		}
		return list;
	}

	@Override
	public String submitIndent(SubmitIndentDto submitIndentDto) throws Exception {
		IndentEntity indentEntity = new IndentEntity();
		String indentId = Util.generateUUID();
		indentEntity.setId(indentId);
		indentEntity.setUserId(submitIndentDto.getUserId());
		indentEntity.setBookId(submitIndentDto.getBookId());
		indentEntity.setAddress(submitIndentDto.getAddr());
		indentEntity.setReceiverName(submitIndentDto.getReceiverName());
		indentEntity.setReceiverTel(submitIndentDto.getReceiverTel());
		indentEntity.setGeneratTime(Util.nowDate());
		indentEntity.setStatus(0);
		indentEntity.setCommentContent("");
		indentEntity.setCommentLevel(0);
		indentEntity.setCommentTime(null);
		indentDao.insert(indentEntity);
		return indentId;
	}

}
