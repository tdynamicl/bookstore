package cn.lost4found.controller;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import cn.lost4found.common.MyException;
import cn.lost4found.dto.manager.AddBookDto;
import cn.lost4found.dto.manager.HintMessageDto;
import cn.lost4found.dto.manager.QueryBookDto;
import cn.lost4found.dto.manager.QueryIndentDto;
import cn.lost4found.dto.manager.UpdateBookDto;
import cn.lost4found.entity.manager.IndentEntity;
import cn.lost4found.entity.ManagerEntity;
import cn.lost4found.service.impl.ManagerServiceImpl;


@Controller
@SessionAttributes({ "manageruser", "books", "book", "cover", "indents",
		"indent","hint"})
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerServiceImpl managerServiceImpl;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("account") String account,
			@RequestParam("password") String password) throws Exception {
		ModelAndView mav = new ModelAndView();
		ManagerEntity mEntity = managerServiceImpl.login(account, password);
		mav.setViewName("index.jsp");
		mav.addObject("manageruser", mEntity);
		mav.addObject("hint", getHintDto());
		return mav;
	}
	
	@RequestMapping(value = "/loginOut.do", method = RequestMethod.GET)
	public ModelAndView loginOut() throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().removeAttribute("manageruser");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login.jsp");
		return mav;
	}

	@RequestMapping(value = "/addBook.do", method = RequestMethod.POST)
	public ModelAndView addBook(String bookCover, AddBookDto addBookDto)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String bookId = UUID.randomUUID().toString().replaceAll("-", "");
		managerServiceImpl.addBook(bookId, addBookDto);
		managerServiceImpl.uploadCover(bookId, bookCover);
		mav.addObject("addBookTip", "《" + addBookDto.getName() + "》添加成功");
		QueryBookDto queryBookDto = new QueryBookDto();
		queryBookDto.setPageNo(1);
		queryBookDto.setPageSize(5);
		managerServiceImpl.queryBooks(queryBookDto);
		mav.setViewName("viewBook.jsp");
		mav.addObject("books", queryBookDto);
		mav.addObject("hint", getHintDto());
		return mav;
	}

	@RequestMapping(value = "/queryBooks.do", method = RequestMethod.POST)
	public ModelAndView queryBooks(QueryBookDto queryBookDto) throws Exception {
		ModelAndView mav = new ModelAndView();
		managerServiceImpl.queryBooks(queryBookDto);
		mav.setViewName("viewBook.jsp");
		mav.addObject("books", queryBookDto);
		return mav;
	}

	@RequestMapping(value = "/queryBook.do", method = RequestMethod.GET)
	public ModelAndView queryBook(QueryBookDto queryBookDto) throws Exception {
		// ModelAndView mav = new ModelAndView();
		// managerServiceImpl.queryBooks(queryBookDto);
		// mav.addObject("books", queryBookDto);
		// mav.setViewName("viewBook.jsp");
		return queryBooks(queryBookDto);
	}

	@RequestMapping(value = "/delBook.do", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView delBook(String strIds) throws Exception {
		ModelAndView mav = new ModelAndView();
		String[] ids = strIds.split(",");
		for (String id : ids) {
			managerServiceImpl.delBook(id);
		}
		mav.addObject("hint", getHintDto());
		return mav;
	}

	@RequestMapping(value = "/checkBook.do", method = RequestMethod.GET)
	public ModelAndView modBook(String id) throws Exception {
		ModelAndView mav = new ModelAndView();
		UpdateBookDto updateBookDto = managerServiceImpl.existBook(id);
		String image = managerServiceImpl.useCover(id);
		mav.addObject("book", updateBookDto);
		if (image != null) {
			mav.addObject("cover", image);
		}
		mav.setViewName("bookInfo.jsp");
		return mav;
	}

	@RequestMapping(value = "/updateBook.do", method = RequestMethod.POST)
	public ModelAndView updateBook(String bookCover, UpdateBookDto updateBookDto)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		managerServiceImpl.updateBook(updateBookDto);
		if (bookCover != null || !"".equals(bookCover)) {
			managerServiceImpl.uploadCover(updateBookDto.getId(), bookCover);
		}
		mav.addObject("addBookTip", "《" + updateBookDto.getName() + "》修改成功");
		mav.setViewName("viewBook.jsp");
		mav.addObject("hint", getHintDto());
		return mav;
	}

	@RequestMapping(value = "/addBookCover.do")
	@ResponseBody
	public ModelAndView addBookCover(String bookId, String bookCover)
			throws Exception {
		// System.out.println(bookId);
		// System.out.println(bookCover);
		managerServiceImpl.uploadCover(bookId, bookCover);
		return modBook(bookId);
	}

	@RequestMapping(value = "/queryIndent.do")
	public ModelAndView queryIndent(QueryIndentDto queryIndentDto)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		managerServiceImpl.queryIndents(queryIndentDto);
		mav.addObject("indents", queryIndentDto);
		mav.setViewName("/manager/indent/viewIndent.jsp");
		return mav;
	}
	
	@RequestMapping(value="queryS2Indent.do")
	public ModelAndView queryS2Indent(QueryIndentDto queryIndentDto) throws Exception{
		queryIndentDto.setStatus(2);
		return queryIndent(queryIndentDto);
	}
	
	@RequestMapping(value="/checkIndent.do")
	public ModelAndView checkIndent(String id)throws Exception{
		ModelAndView mav=new ModelAndView();
		IndentEntity indentEntity=managerServiceImpl.checkIndents(id);
		mav.addObject("indent", indentEntity);
		mav.addObject("hint", getHintDto());
		mav.setViewName("/manager/indent/indentInfo.jsp");
		return mav;
	}
	
	@RequestMapping(value="/changeIndent.do")
	public ModelAndView changeIndent(String id,int status)throws Exception{
		managerServiceImpl.changeIndent(id, status);
		return checkIndent(id);
	}
	
	@RequestMapping(value="/delExBook.do")
	public ModelAndView delExBook(String id)throws Exception{
		ModelAndView mav=new ModelAndView();
		managerServiceImpl.deleteExBook(id);
		mav.addObject("hint", getHintDto());
		return mav;
	}
	
	@RequestMapping(value="/updateExBook.do")
	public ModelAndView updateExBook(String id,String image)throws Exception{
		ModelAndView mav=new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value="/updateExBookCover.do")
	public ModelAndView updateExBookCover(String bookCover,String bookId)throws Exception{
		ModelAndView mav=new ModelAndView();
		managerServiceImpl.changeExBookCover(bookId, bookCover);
		return mav;
	}
	
	@RequestMapping(value="pyBook.do")
	public ModelAndView pyBook(String oldId,String newId)throws Exception{
		managerServiceImpl.pyBook(oldId, newId);
		return queryBook(new QueryBookDto());
	}
	
	public HintMessageDto getHintDto() throws Exception{
		HintMessageDto hintMessageDto=managerServiceImpl.getHint();
		return hintMessageDto;
	}
	
	@ExceptionHandler(MyException.class)
	public ModelAndView exceptionHandler(MyException e) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login.jsp");
		mav.addObject("errtip", e.getMessage());
		return mav;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView defExceptionHandler(Exception e) throws Exception{
		e.printStackTrace();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login.jsp");
		mav.addObject("errtip", e.getMessage());
		return mav;
	}
	
	
}
