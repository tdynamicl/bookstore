package cn.lost4found.service.impl;

import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lost4found.dao.BookDao;
import cn.lost4found.dao.BookImageDao;
import cn.lost4found.dao.FavoriteDao;
import cn.lost4found.dao.IndentDao;
import cn.lost4found.dao.KeywordDao;
import cn.lost4found.dao.RefBookKeywordDao;
import cn.lost4found.dao.UserDao;
import cn.lost4found.dto.BookInfoDto;
import cn.lost4found.dto.IndentDto;
import cn.lost4found.dto.SubmitIndentDto;
import cn.lost4found.dto.UserRegisterDto;
import cn.lost4found.entity.BookEntity;
import cn.lost4found.entity.FavoriteEntity;
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
	@Autowired
	private FavoriteDao favoriteDao;
	@Autowired
	private BookImageDao bookImageDao;
	
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
		Double avg = indentDao.queryAvgOfCommentLevelByBookId(id);
		bookInfoDto.setRankTotal(indentDao.queryTotalOfCommentByBookId(id));
		bookInfoDto.setRankLevel((avg==null||avg==0)?"暂无":String.format("%.1f", avg));
		return bookInfoDto;
	}

	@Override
	public String loadBookImage(String bookId) throws Exception {
		String bookImageStream = bookImageDao.selectOneByBookId(bookId);
		if (bookImageStream==null) {
			throw new MyException("此书暂无封面");
		}
		return bookImageStream;
	}
	
	@Override
	public LinkedList<BookInfoDto> loadBookInfosByCategory(String word, int index) throws Exception {
		KeywordEntity keywordEntity = keywordDao.select("keyword", word);
		if (keywordEntity==null) {
			throw new MyException("没有此图书分类");
		}
		LinkedList<String> bookIds = refBookKeywordDao.queryByKeywordIdLimited(keywordEntity.getId(), index, 7);
		if (bookIds.isEmpty()) {
			throw new MyException("暂无更多此分类图书");
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
		indentEntity.setGenerateTime(Util.nowDate());
		indentEntity.setStatus(1);
		indentEntity.setCommentContent(null);
		indentEntity.setCommentLevel(0);
		indentEntity.setCommentTime(null);
		indentDao.insert(indentEntity);
		return indentId;
	}

	@Override
	public IndentEntity loadIndentInfo(String id, String userId) throws Exception {
		IndentEntity indentEntity = indentDao.select("id", id);
		checkIndentOwner(indentEntity, userId);
		return indentEntity;
	}

	@Override
	public void purchaseIndent(String id, String userId) throws Exception {
		IndentEntity indentEntity = indentDao.select("id", id);
		checkIndentOwner(indentEntity, userId);
		if (indentEntity.getStatus() != 1) {
			throw new MyException("该订单不处于“待支付”状态");
		}
		indentEntity.setStatus(2);
		indentDao.update(indentEntity);
	}

	@Override
	public void cancelIndent(String id, String userId) throws Exception {
		IndentEntity indentEntity = indentDao.select("id", id);
		checkIndentOwner(indentEntity, userId);
/*		if (indentEntity.getStatus() != 2) {
			throw new MyException("该订单不处于“”状态");
		}*/
		indentEntity.setStatus(0);
		indentDao.update(indentEntity);
	}

	@Override
	public void receivedIndent(String id, String userId) throws Exception {
		IndentEntity indentEntity = indentDao.select("id", id);
		checkIndentOwner(indentEntity, userId);
		if (indentEntity.getStatus() != 3) {
			throw new MyException("该订单不处于“待收货”状态");
		}
		indentEntity.setStatus(4);
		indentDao.update(indentEntity);
	}

	@Override
	public void rateIndent(String id, String userId, String content, int level) throws Exception {
		IndentEntity indentEntity = indentDao.select("id", id);
		checkIndentOwner(indentEntity, userId);
		if (indentEntity.getStatus() != 4) {
			throw new MyException("该订单不处于“已收货，未评价”状态");
		}
		indentEntity.setStatus(5);
		indentDao.update(indentEntity);
	}

	@Override
	public void deleteIndent(String id, String userId) throws Exception {
		IndentEntity indentEntity = indentDao.select("id", id);
		checkIndentOwner(indentEntity, userId);
		/*if (indentEntity.getStatus() != 5) {
			throw new MyException("该订单不处于“”状态");
		}*/
		indentEntity.setStatus(0);
		indentDao.update(indentEntity);
	}

	private void checkIndentOwner(IndentEntity indentEntity, String userId) throws Exception{
		if (indentEntity==null || indentEntity.getStatus() == 0) {
			throw new MyException("该订单已失效，或已被删除");
		}
		if (!indentEntity.getUserId().equals(userId)) {
			throw new MyException("该订单（编号："+ indentEntity.getId() +"）不属于你，无法查看或操作");
		}
	}

	@Override
	public LinkedList<IndentDto> loadUnfinishedIndent(String userId) throws Exception {
		LinkedList<IndentEntity> entityList = indentDao.queryAllUnfinishedIndentByUserId(userId);
		return entityList2dtoList(entityList);
	}

	@Override
	public LinkedList<IndentDto> loadFinishedIndent(String userId) throws Exception {
		LinkedList<IndentEntity> entityList = indentDao.queryAllFinishedIndentByUserId(userId);
		return entityList2dtoList(entityList);
	}
	
	private LinkedList<IndentDto> entityList2dtoList(LinkedList<IndentEntity> entityList) throws Exception{
		LinkedList<IndentDto> dtoList = new LinkedList<>();
		IndentDto tempDto = null;
		BookEntity tempBookEntity = null;
		for(IndentEntity i : entityList){
			tempDto = new IndentDto();
			tempDto.setId(i.getId());
			tempDto.setGenerateTime(i.getGenerateTime());
			tempDto.setBookId(i.getBookId());
			tempBookEntity = bookDao.select("id", i.getBookId());
			tempDto.setBookName(tempBookEntity.getName());
			tempDto.setStatus(i.getStatus());
			dtoList.add(tempDto);
		}
		return dtoList;
	}

	@Override
	public void addFavoriteBook(String bookId, String userId) throws Exception {
		if (checkFavorite(bookId, userId)) {
			throw new MyException("您已经收藏了此书！");
		}
		FavoriteEntity favoriteEntity = new FavoriteEntity();
		favoriteEntity.setId(Util.generateUUID());
		favoriteEntity.setBookId(bookId);
		favoriteEntity.setUserId(userId);
		favoriteEntity.setAddTime(Util.nowDate());
		favoriteDao.insert(favoriteEntity);
	}

	@Override
	public void delFavoriteBook(String bookId, String userId) throws Exception {
		if (favoriteDao.selectByBookIdAndUserId(bookId, userId)==null) {
			throw new MyException("取消了收藏！");
		}
		favoriteDao.deleteByBookIdAndUserId(bookId, userId);
	}

	@Override
	public boolean checkFavorite(String bookId, String userId) throws Exception {
		return favoriteDao.selectByBookIdAndUserId(bookId, userId)!=null;
	}

	@Override
	public LinkedList<BookInfoDto> queryByNameDescAuthorPressLimited(String keyword, int index, int limit) throws Exception {
		keyword = "%" + keyword + "%";
		LinkedList<String> ids = bookDao.queryByNameDescAuthorPressLimited(keyword, index, limit);
		if (ids.isEmpty()) {
			throw new MyException("没有更多搜索结果了");
		}
		LinkedList<BookInfoDto> dtos = new LinkedList<>();
		for(String id : ids){
			dtos.add(loadBookInfo(id));
		}
		return dtos;
	}


	
}
