package cn.xxywithpq.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.POST)
	public Object Login(HttpSession session, @RequestHeader HttpHeaders headers, HttpServletRequest request,
			HttpServletRequest response, Authentication authentication) {
		JSONObject responseJson = new JSONObject();
		String x_auth_token = (String) session.getId();
		responseJson.put("flag", true);
		responseJson.put("msg", "Login success!");
		responseJson.put("x_auth_token", x_auth_token);
		return responseJson;
	}

	@RequestMapping(method = RequestMethod.POST, value = "loginfail")
	public Object loginFail(HttpServletRequest request, HttpServletResponse response) {
		JSONObject object = new JSONObject();
		object.put("msg", "登陆失败");
		return object;
	}
}
