package cn.xxywithpq.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

	@RequestMapping(method = RequestMethod.POST)
	public Object Login(HttpSession session, @RequestHeader HttpHeaders headers, HttpServletRequest request,
			HttpServletRequest response, Authentication authentication) {
		Map<String, Object> responseJson = Maps.newLinkedHashMap();

		String x_auth_token = session.getId();
		responseJson.put("flag", true);
		responseJson.put("msg", "Login success!");
		responseJson.put("x_auth_token", x_auth_token);
		return responseJson;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Object LoginX(HttpSession session, @RequestHeader HttpHeaders headers, HttpServletRequest request,
			HttpServletRequest response, Authentication authentication) {
		Map<String, Object> object = Maps.newLinkedHashMap();
		object.put("flag", true);
		return object;
	}

	@RequestMapping(method = RequestMethod.POST, value = "loginfail")
	public Object loginFail(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> object = Maps.newLinkedHashMap();
		
		object.put("msg", "登陆失败");
		return object;
	}
}
