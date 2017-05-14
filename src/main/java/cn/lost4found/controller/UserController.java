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
import cn.lost4found.dto.PurchaseCartDto;
import cn.lost4found.dto.SubmitIndentDto;
import cn.lost4found.dto.UserRegisterDto;
import cn.lost4found.dto.json.JsonRespose;
import cn.lost4found.entity.IndentEntity;
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
		jsonRespose.setData(null);
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="/loadBookInfo.do", method=RequestMethod.POST)
	public ModelAndView loadBookInfo(@RequestParam("id")String id) throws Exception{
		BookInfoDto bookInfoDto = userServiceImpl.loadBookInfo(id);
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(bookInfoDto);
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="/loadBookImage.do", method=RequestMethod.POST)
	public ModelAndView loadBookImage(@RequestParam("id")String id) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(userServiceImpl.loadBookImage(id));
		return jsonRespose.toModelAndView();
	}
	
	
	@RequestMapping(value="loadBookInfosByCategory.do", method=RequestMethod.POST)
	public ModelAndView loadBookInfosByCategory(@RequestParam("word")String word, @RequestParam("index")int index) throws Exception{
		List<BookInfoDto> resultList = userServiceImpl.loadBookInfosByCategory(word, index);
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(resultList);
		return jsonRespose.toModelAndView();
	}

	@RequestMapping(value="submitIndent.do", method=RequestMethod.POST)
	public ModelAndView submitIndent(SubmitIndentDto submitIndentDto) throws Exception{
		String indentId = userServiceImpl.submitIndent(submitIndentDto);
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(indentId);
		jsonRespose.setMessage("订单提交成功，即将前往支付");
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="loadIndentInfo.do", method=RequestMethod.POST)
	public ModelAndView loadIndentInfo(@RequestParam("id")String id, @RequestParam("userId")String userId) throws Exception{
		IndentEntity indentEntity = userServiceImpl.loadIndentInfo(id, userId);
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(indentEntity);
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="purchaseIndent.do", method=RequestMethod.POST)
	public ModelAndView purchaseIndent(@RequestParam("id")String id, @RequestParam("userId")String userId) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		userServiceImpl.purchaseIndent(id, userId);
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="cancelIndent.do", method=RequestMethod.POST)
	public ModelAndView cancelIndent(@RequestParam("id")String id, @RequestParam("userId")String userId) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		userServiceImpl.cancelIndent(id, userId);
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="receivedIndent.do", method=RequestMethod.POST)
	public ModelAndView receivedIndent(@RequestParam("id")String id, @RequestParam("userId")String userId) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		userServiceImpl.receivedIndent(id, userId);
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="rateIndent.do", method=RequestMethod.POST)
	public ModelAndView rateIndent(@RequestParam("id")String id, @RequestParam("userId")String userId, @RequestParam("content")String content, @RequestParam("level")int level) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		userServiceImpl.rateIndent(id, userId, content, level);
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="deleteIndent.do", method=RequestMethod.POST)
	public ModelAndView deleteIndent(@RequestParam("id")String id, @RequestParam("userId")String userId) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		userServiceImpl.deleteIndent(id, userId);
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="loadUnfinishedIndent.do", method=RequestMethod.POST)
	public ModelAndView loadUnfinishedIndent(@RequestParam("userId")String userId) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(userServiceImpl.loadUnfinishedIndent(userId));
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="loadFinishedIndent.do", method=RequestMethod.POST)
	public ModelAndView loadFinishedIndent(@RequestParam("userId")String userId) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(userServiceImpl.loadFinishedIndent(userId));
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="addToCart.do", method=RequestMethod.POST)
	public ModelAndView addToCart(@RequestParam("bookId")String bookId, @RequestParam("userId")String userId) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		userServiceImpl.addToCart(bookId, userId);
		jsonRespose.setMessage("添加成功");
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="loadCartInfo.do", method=RequestMethod.POST)
	public ModelAndView loadCartInfo(@RequestParam("userId")String userId) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(userServiceImpl.loadCartUnpurchased(userId));
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="addFavoriteBook.do", method=RequestMethod.POST)
	public ModelAndView addFavoriteBook(@RequestParam("bookId")String bookId, @RequestParam("userId")String userId) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		userServiceImpl.addFavoriteBook(bookId, userId);
		jsonRespose.setMessage("添加成功");
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="delFavoriteBook.do", method=RequestMethod.POST)
	public ModelAndView delFavoriteBook(@RequestParam("bookId")String bookId, @RequestParam("userId")String userId) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		userServiceImpl.delFavoriteBook(bookId, userId);
		jsonRespose.setMessage("移除收藏成功");
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="checkFavorite.do", method=RequestMethod.POST)
	public ModelAndView checkFavorite(@RequestParam("bookId")String bookId, @RequestParam("userId")String userId) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(userServiceImpl.checkFavorite(bookId, userId));
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="search.do", method=RequestMethod.POST)
	public ModelAndView search(@RequestParam("keyword")String keyword, @RequestParam("index")int index) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(userServiceImpl.queryByNameDescAuthorPressLimited(keyword, index, 7));
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="loadNewBook.do", method=RequestMethod.POST)
	public ModelAndView loadNewBook() throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(userServiceImpl.queryNewBook(4));
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="loadComment.do", method=RequestMethod.POST)
	public ModelAndView loadComment(@RequestParam("bookId")String bookId, @RequestParam("index")int index) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(userServiceImpl.queryComments(bookId, index));
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="loadUserIcon.do", method=RequestMethod.POST)
	public ModelAndView loadUserIcon(@RequestParam("userId")String userId) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(userServiceImpl.loadUserIcon(userId));
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="loadExihibitionBook.do", method=RequestMethod.POST)
	public ModelAndView loadExihibitionBook() throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(userServiceImpl.loadExihibitionBook());
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="purchaseCart.do", method=RequestMethod.POST)
	public ModelAndView purchaseCart(PurchaseCartDto purchaseCartDto) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		userServiceImpl.purchaseCart(purchaseCartDto);
		jsonRespose.setMessage("支付成功！");
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="removeCartItem.do", method=RequestMethod.POST)
	public ModelAndView removeCartItem(@RequestParam("userId")String userId, @RequestParam("id")String id) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		userServiceImpl.removeCartItem(userId, id);
		jsonRespose.setMessage("移除成功");
		return jsonRespose.toModelAndView();
	}
	
	@RequestMapping(value="loadFavoritBook.do", method=RequestMethod.POST)
	public ModelAndView loadFavoritBook(@RequestParam("userId")String userId, @RequestParam("index")int index) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setData(userServiceImpl.loadFavoriteBook(userId, index, 5));
		return jsonRespose.toModelAndView();
	}
	
	//-------ExceptionHandlers--------------
	@ExceptionHandler(MyException.class)
	public ModelAndView exceptionHandler(MyException e) throws Exception{
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setCode(e.getErrorCode());
		jsonRespose.setMessage(e.getMessage());
		return jsonRespose.toModelAndView();
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView defExceptionHandler(Exception e) throws Exception{
		e.printStackTrace();
		JsonRespose jsonRespose = new JsonRespose();
		jsonRespose.setCode(1001);
		jsonRespose.setMessage("发生未知错误，请稍后重试");
		return jsonRespose.toModelAndView();
	}
}
