package cn.xxywithpq.security;

import java.util.List;
import java.util.Set;

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

@Service
public class MyUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private UserService userService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		StandardPasswordEncoder s = new StandardPasswordEncoder("oak");
		System.out.println("加密后的密码:" + userDetails.getPassword());
		if(!s.matches(authentication.getCredentials().toString(), userDetails.getPassword())){
			throw new BadCredentialsException(messages.getMessage(
					"密码校验错误",
					"Bad credentials"));
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		cn.xxywithpq.domain.User user = null;
		if (null != username && !"".equals(username.trim())) {
			user = userService.findOne(username);
		}

		List<Role> roles = user.getRoles();

		Set<GrantedAuthority> authorities = Sets.newHashSet();

		for (Role role : roles) {
			authorities.addAll(role.getAuthorities());
		}

		return new User(user.getUsername(), user.getPassword(), authorities);
	}

}
