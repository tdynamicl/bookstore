package cn.lost4found.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import cn.lost4found.common.MyException;
import cn.lost4found.dto.AddBookDto;
import cn.lost4found.entity.ManagerEntity;
import cn.lost4found.service.impl.ManagerServiceImpl;

@Controller
@SessionAttributes("manageruser")
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private ManagerServiceImpl managerServiceImpl;
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("account")String account, @RequestParam("password")String password) throws Exception{
		ModelAndView mav = new ModelAndView();
		ManagerEntity mEntity =  managerServiceImpl.login(account, password);
		mav.setViewName("index.jsp");
		mav.addObject("manageruser", mEntity);
		return mav;
	}
	
	@RequestMapping(value="/addBook.do", method=RequestMethod.POST)
	public ModelAndView addBook(AddBookDto addBookDto) throws Exception{
		ModelAndView mav = new ModelAndView();
		managerServiceImpl.addBook(addBookDto);
		mav.setViewName("viewBook.jsp");
		mav.addObject("addBookTip", "《" + addBookDto.getName() + "》添加成功");
		return mav;
	}
	
	@ExceptionHandler(MyException.class)
	public ModelAndView exceptionHandler(MyException e) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login.jsp");
		mav.addObject("errtip", e.getMessage());
		return mav;
	}
	
}
