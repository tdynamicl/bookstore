package cn.lost4found.dto.json;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JsonRespose {
	
	private int code = 0;
	private String message = "";
	private Object data = null;
	
	public ModelAndView toModelAndView(){
		ModelAndView mav = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		Map<String, Object> result = new HashMap<>();
		result.put("code", this.code);
		result.put("message", this.message);
		result.put("data", this.data);
		jsonView.setAttributesMap(result);
		mav.setView(jsonView);
		return mav;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
