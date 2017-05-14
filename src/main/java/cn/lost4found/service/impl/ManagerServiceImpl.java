package cn.lost4found.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lost4found.common.MyException;
import cn.lost4found.common.Util;
import cn.lost4found.dao.BookDao;
import cn.lost4found.dao.CoverDao;
import cn.lost4found.dao.ExBookDao;
import cn.lost4found.dao.IndentDao;
import cn.lost4found.dao.ManagerDao;
import cn.lost4found.dao.RefBookKeywordDao;
import cn.lost4found.dto.manager.AddBookDto;
import cn.lost4found.dto.manager.HintMessageDto;
import cn.lost4found.dto.manager.QueryBookDto;
import cn.lost4found.dto.manager.QueryIndentDto;
import cn.lost4found.dto.manager.UpdateBookDto;
import cn.lost4found.entity.BookEntity;
import cn.lost4found.entity.CoverEntity;
import cn.lost4found.entity.ExBookEntity;
import cn.lost4found.entity.manager.IndentEntity;
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
	@Autowired
	private CoverDao coverDao;
	@Autowired
	private IndentDao indentDao;
	@Autowired
	private ExBookDao exBookDao;
	
	@Override
	public ManagerEntity login(String account, String password)
			throws Exception {
		if (account == null || account.trim().length() == 0) {
			throw new MyException("用户名不能为空");
		}
		if (password == null || password.trim().length() == 0) {
			throw new MyException("密码不能为空");
		}
		ManagerEntity managerEntity = managerDao.select("account", account);
		if (managerEntity == null) {
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
		bookEntity.setPressTime(AddBookDto.sdf.parse(addBookDto
				.getPressTimeString()));
		bookEntity.setAddTime(new Date(System.currentTimeMillis()));
		bookDao.insert(bookEntity);
		for (String k : addBookDto.getKeyword()) {
			refBookKeywordEntity.setId(UUID.randomUUID().toString()
					.replaceAll("-", ""));
			refBookKeywordEntity.setKeywordId(k);
			refBookKeywordEntity.setBookId(bookId);
			refBookKeywordDao.insert(refBookKeywordEntity);
		}
	}

	public void addBook(String bookId,AddBookDto addBookDto) throws Exception {
		BookEntity bookEntity = new BookEntity();
		RefBookKeywordEntity refBookKeywordEntity = new RefBookKeywordEntity();
		bookEntity.setId(bookId);
		bookEntity.setName(addBookDto.getName());
		bookEntity.setDescription(addBookDto.getDescription());
		bookEntity.setAuthor(addBookDto.getAuthor());
		bookEntity.setPress(addBookDto.getPress());
		bookEntity.setPrice(addBookDto.getPrice());
		bookEntity.setPressTime(AddBookDto.sdf.parse(addBookDto
				.getPressTimeString()));
		bookEntity.setAddTime(new Date(System.currentTimeMillis()));
		bookDao.insert(bookEntity);
		String keywords[]=addBookDto.getKeyword();
		if(keywords!=null){
			for (String k : keywords) {
				refBookKeywordEntity.setId(UUID.randomUUID().toString()
						.replaceAll("-", ""));
				refBookKeywordEntity.setKeywordId(k);
				refBookKeywordEntity.setBookId(bookId);
				refBookKeywordDao.insert(refBookKeywordEntity);
			}
		}
	}
	@Override
	public void queryBooks(QueryBookDto queryBookDto) throws Exception {
		String key = queryBookDto.getName();
		if (key == null ||"null".equals(key)|| "".equals(key)) {
			key = "%%";
		}else{
			key="%"+key+"%";
		}
		int pageNo = queryBookDto.getPageNo();
		int pageSize = queryBookDto.getPageSize();
		int first=(pageNo - 1) * pageSize;
		int end=pageSize;
		LinkedList<BookEntity> bookList = bookDao.selectByKey(key, first, end);
		LinkedList<ExBookEntity> exBookList=exBookDao.selectExBook();
		for (ExBookEntity exBookEntity : exBookList) {
			exBookEntity.setName(bookDao.select("id", exBookEntity.getId()).getName());
		}
		queryBookDto.setBooks(bookList);
		queryBookDto.setExBooks(exBookList);
		int totalRows = bookDao.selectTotalRows(key);
		queryBookDto.setTotalRows(totalRows);
		queryBookDto.setTotalPages((int) Math.ceil((double) totalRows/ pageSize));
	}

	@Override
	public void delBook(String id) throws Exception {
		bookDao.delete("id", id);
		refBookKeywordDao.delete("book_id", id);
		exBookDao.delete("book_id", id);
	}

	@Override
	public void updateBook(UpdateBookDto updateBookDto) throws Exception {
		String id=updateBookDto.getId();
		BookEntity bookEntity=bookDao.select("id", id);
		bookEntity.setName(updateBookDto.getName());
		bookEntity.setDescription(updateBookDto.getDescription());
		bookEntity.setAuthor(updateBookDto.getAuthor());
		bookEntity.setPress(updateBookDto.getPress());
		bookEntity.setPressTime(UpdateBookDto.sdf.parse(updateBookDto.getPressTimeString()));
		bookEntity.setPrice(updateBookDto.getPrice());
		bookDao.update(bookEntity);
		refBookKeywordDao.delete("book_id", id);
		RefBookKeywordEntity refBookKeywordEntity=new RefBookKeywordEntity();
		refBookKeywordEntity.setBookId(id);
		int[] keywords=updateBookDto.getKeywords();
		if(keywords!=null){
			for (int i : keywords) {
				refBookKeywordEntity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				refBookKeywordEntity.setKeywordId(i+"");
				refBookKeywordDao.insert(refBookKeywordEntity);
			}
		}
	}

	@Override
	public UpdateBookDto existBook(String id) throws Exception {
		BookEntity bookEntity = bookDao.select("id", id);
		UpdateBookDto updateBookDto = new UpdateBookDto();
		updateBookDto.setId(id);
		updateBookDto.setName(bookEntity.getName());
		updateBookDto.setDescription(bookEntity.getDescription());
		updateBookDto.setAuthor(bookEntity.getAuthor());
		updateBookDto.setPress(bookEntity.getPress());
		updateBookDto.setPressTimeString(UpdateBookDto.sdf.format(bookEntity.getPressTime()));
		updateBookDto.setPrice(bookEntity.getPrice());
		updateBookDto.setAddTimeString(UpdateBookDto.sdf.format(bookEntity.getAddTime()));
		updateBookDto.setName(bookEntity.getName());
		LinkedList<RefBookKeywordEntity> keywordList = refBookKeywordDao.selectByBookId(
				"book_id", id);
		int[] keywords=new int[keywordList.size()];
		int len=0;
		for (RefBookKeywordEntity entity : keywordList) {
			keywords[len++]=Integer.parseInt(entity.getKeywordId());
		}
		updateBookDto.setKeywords(keywords);
		return updateBookDto;
	}

	@Override
	public void uploadCover(String bookId,String cover) throws Exception {
		coverDao.delete("book_id", bookId);
		CoverEntity coverEntity=new CoverEntity();
		coverEntity.setId(Util.generateUUID());
		coverEntity.setBookId(bookId);
		coverEntity.setImageString(cover);
		coverDao.insert(coverEntity);
	}

	@Override
	public String useCover(String bookId) throws Exception {
		String cover=null;
		if(coverDao.exist("book_id", bookId)==1){
			cover = coverDao.selectByBookId("book_id", bookId);
		}
		return cover;
	}

	@Override
	public void queryIndents(QueryIndentDto queryIndentDto) throws Exception {
		int pageNo=queryIndentDto.getPageNo();
		int pageSize=queryIndentDto.getPageSize();
		String bookName=queryIndentDto.getBookName();
		String userName=queryIndentDto.getUserName();
		String generateTime=queryIndentDto.getGenerateTimeStr();
		int status=queryIndentDto.getStatus();
		int first=(pageNo - 1) * pageSize;
		int end=pageSize;
		LinkedList<IndentEntity> indents=indentDao.selectByDto(bookName, userName, generateTime,status, first, end);
		int totalRows=indentDao.selectCountByDto(bookName, userName, generateTime,status, first, end);
		queryIndentDto.setTotalRows(totalRows);
		queryIndentDto.setTotalPages((int) Math.ceil((double) totalRows/ pageSize));
		queryIndentDto.setIndents(indents);
	}

	@Override
	public IndentEntity checkIndents(String id) throws Exception {
		IndentEntity indentEntity=indentDao.selectById(id);
		return indentEntity;
	}

	@Override
	public void changeIndent(String id, int status) throws Exception {
		indentDao.changeIndentStatus(id, status);
	}

	@Override
	public void deleteExBook(String id) throws Exception {
		exBookDao.delete("book_id", id);
	}

	@Override
	public void changeExBookCover(String bookId, String image) throws Exception {
		exBookDao.changeExBookCover(bookId, image);
	}

	@Override
	public void pyBook(String oldId, String newId) throws Exception {
		if(!(oldId==null||"".equals(oldId.trim()))){
			exBookDao.delete("book_id", oldId);
		}
		exBookDao.insertById(newId);
	}

	@Override
	public HintMessageDto getHint() throws Exception {
		HintMessageDto hintMessageDto=new HintMessageDto();
		int sTwo=indentDao.selectCountByDto(null, null, null, 2, 0, 10);
		hintMessageDto.setsTwo(sTwo);
		hintMessageDto.setAllBookNumber(bookDao.selectTotalRows("%%"));
		hintMessageDto.setEduBookNumber(refBookKeywordDao.selectCountByClass("education"));
		hintMessageDto.setNovelBookNumber(refBookKeywordDao.selectCountByClass("novel"));
		hintMessageDto.setArtBookNumber(refBookKeywordDao.selectCountByClass("art"));
		hintMessageDto.setChildBookNumber(refBookKeywordDao.selectCountByClass("child"));
		hintMessageDto.setLiveBookNumber(refBookKeywordDao.selectCountByClass("live"));
		hintMessageDto.setScienceBookNumber(refBookKeywordDao.selectCountByClass("science"));
		return hintMessageDto;
	}
	
}
