package cn.lost4found.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cn.lost4found.common.MyException;
import cn.lost4found.dto.BookInfoDto;
import cn.lost4found.dto.SubmitIndentDto;
import cn.lost4found.dto.UserRegisterDto;
import cn.lost4found.dto.json.JsonRespose;
import cn.lost4found.entity.UserEntity;
import cn.lost4found.service.impl.UserServiceImpl;

@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("account")String account, @RequestParam("password")String password) throws Exception{
		UserEntity userEntity = userServiceImpl.login(account, password);
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setMessage("登录成功！");
		jsonRespose.setData(userEntity);
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="/register.do", method=RequestMethod.POST)
	public ModelAndView register(UserRegisterDto userDto) throws Exception{
		userServiceImpl.register(userDto);
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setMessage("注册成功！");
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="/loadBookInfo.do", method=RequestMethod.POST)
	public ModelAndView loadBookInfo(@RequestParam("id")String id) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		BookInfoDto bookInfoDto = userServiceImpl.loadBookInfo(id);
		jsonRespose.setData(bookInfoDto);
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="loadBookInfosByCategory.do", method=RequestMethod.POST)
	public ModelAndView loadBookInfosByCategory(@RequestParam("word")String word, @RequestParam("index")int index) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		List<BookInfoDto> resultList = userServiceImpl.loadBookInfosByCategory(word, index);
		jsonRespose.setData(resultList);
		return jsonRespose.toModelAndView();
	}

	@RequestMapping(value="submitIndent.do", method=RequestMethod.POST)
	public ModelAndView submitIndent(SubmitIndentDto submitIndentDto) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		String indentId = userServiceImpl.submitIndent(submitIndentDto);
		jsonRespose.setData(indentId);
		jsonRespose.setMessage("订单提交成功，即将前往支付");
		return jsonRespose.toModelAndView();
	}
	
	
	
	
	@ExceptionHandler(MyException.class)
	public ModelAndView exceptionHandler(MyException e) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setCode(1001);
		jsonRespose.setMessage(e.getMessage());
		return jsonRespose.toModelAndView();
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView defExceptionHandler(Exception e) throws Exception{
		e.printStackTrace();
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setCode(1101);
		jsonRespose.setMessage("发生未知错误，请稍后重试");
		return jsonRespose.toModelAndView();
	}
}
