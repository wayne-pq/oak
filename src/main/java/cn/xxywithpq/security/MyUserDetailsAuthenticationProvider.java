package cn.xxywithpq.security;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import cn.xxywithpq.domain.Role;
import cn.xxywithpq.service.UserService;

/**
 * 身份校验服务
 * @author panqian
 * @date 2016年11月21日 上午10:02:47
 */
@Service
public class MyUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private final static Log log = LogFactory.getLog(MyUserDetailsAuthenticationProvider.class);

	@Autowired
	private UserService userService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		StandardPasswordEncoder s = new StandardPasswordEncoder("oak");
		log.info("after Encoder : " + userDetails.getPassword());
		if (!s.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
			log.error("Bad credentials !!! ");
			throw new BadCredentialsException(messages.getMessage("密码校验错误", "Bad credentials"));
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		cn.xxywithpq.domain.User user = null;
		if (null != username && !"".equals(username.trim())) {
			user = userService.findOne(username);
		}

		Set<Role> roles = user.getRoles();

		Set<GrantedAuthority> authorities = Sets.newHashSet();

		for (Role role : roles) {
			authorities.addAll(role.getAuthorities());
		}

		log.info("user " + user.getUsername() + " attempt to login!!!");

		return new User(user.getUsername(), user.getPassword(), authorities);
	}

}
