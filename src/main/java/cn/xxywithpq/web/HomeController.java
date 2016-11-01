package cn.xxywithpq.web;

import java.security.Principal;

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
@RequestMapping("/home/")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET, value = "login")
	public Object LoginGet(HttpSession session,@RequestHeader HttpHeaders headers,HttpServletRequest request,HttpServletRequest response,Authentication authentication,Principal principal) {
		JSONObject object = new JSONObject();
		object.put("msg", "ok");
		return object;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "login")
	public Object Login(HttpSession session,@RequestHeader HttpHeaders headers,HttpServletRequest request,HttpServletRequest response,Authentication authentication) {
		JSONObject responseJson = new JSONObject();
		String x_auth_token = (String) session.getId();
		responseJson.put("flag", true);
		responseJson.put("msg", "Login success!");
		responseJson.put("x_auth_token", x_auth_token);
		return responseJson;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "loginsuccess")
	public Object loginSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) {
		HttpSession session = request.getSession();
		System.out.println(session.getId());
//		Cookie cookie = new Cookie("SESSION", "880110dd-d24f-48b3-9fc7-95c118cedbb1");
//		response.addCookie(cookie);
		JSONObject object = new JSONObject();
//		User myPrincipal = (User) authentication.getPrincipal();
//		Collection<GrantedAuthority> authorities = myPrincipal.getAuthorities();
//		ArrayList<GrantedAuthority> arrayList = new ArrayList<GrantedAuthority>(authorities);
//		User principal = new User(myPrincipal.getUsername(), "", arrayList);
		object.put("sessionid", session.getId());
		object.put("msg", "登陆成功");
		return object;
	}
	@RequestMapping(method = RequestMethod.POST, value = "loginfail")
	public Object loginFail(HttpServletRequest request, HttpServletResponse response) {
		JSONObject object = new JSONObject();
		object.put("msg", "登陆失败");
		return object;
	}
}
