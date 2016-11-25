package cn.xxywithpq.web;

import java.util.LinkedHashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cn.xxywithpq.domain.Role;
import cn.xxywithpq.domain.User;
import cn.xxywithpq.service.RoleService;
import cn.xxywithpq.service.UserService;
import cn.xxywithpq.utils.CryptoUtil;
import cn.xxywithpq.utils.NetUtil;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

	private final static Log log = LogFactory.getLog(AuthorizationController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(method = RequestMethod.POST, value = "/regist")
	public Object regist(HttpSession session, @RequestHeader HttpHeaders headers, HttpServletRequest request,
			HttpServletRequest response, Authentication authentication, User user) {
		Map<String, Object> responseJson = Maps.newLinkedHashMap();

		log.info("begin to regist... ip : " + NetUtil.getIpAddr(request));

		boolean flag = true;

		try {
			if (null != userService.findOne(user.getUsername())) {
				flag = false;
				responseJson.put("flag", flag);
				responseJson.put("msg", "用户名重复");
				log.error("The user name repetition。。。。");
				return responseJson;
			}

			Role userRole = roleService.findOne(new Long("2"));

			LinkedHashSet<Role> sets = Sets.newLinkedHashSet();
			sets.add(userRole);

			user.setRoles(sets);
			user.setEnabled(false);

			user.setPassword(CryptoUtil.encoder(user.getPassword()));

			userService.save(user);
		} catch (Exception e) {
			log.error("regist fail......");
			flag = false;
		}

		responseJson.put("flag", flag);
		// userService.save(user);
		return responseJson;
	}

}
