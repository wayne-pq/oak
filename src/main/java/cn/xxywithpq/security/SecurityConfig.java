package cn.xxywithpq.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * spring-security全局配置
 * 
 * @author panqian
 * @date 2016年10月25日 上午11:31:38
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsAuthenticationProvider authenticationProvider;
//	@Autowired
//	private MySavedRequestAwareAuthenticationSuccessHandler mySavedRequestAwareAuthenticationSuccessHandler;
//	private SpringSessionBackedSessionRegistry sessionRegistry;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http mapsto https
		/*
		 * .portMapper().http(8080).mapsTo(8444) .and()
		 */
		// super.configure(http);
		// http.httpBasic();
		http.cors().and().csrf().disable();
		http
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
		.requestCache()
			.requestCache(new NullRequestCache())
			.and()
		.httpBasic();
		// http.csrf().disable()
		// .formLogin()
		// .loginPage("/login")
		// .and()
		// .authorizeRequests()
		// .mvcMatchers(HttpMethod.GET,"/").authenticated()
		// .anyRequest().permitAll()
		// .and()
//		http.authorizeRequests().antMatchers("/home/**").permitAll().antMatchers("/work/**").authenticated()
//				.and().cors().configurationSource(corsConfigurationSource()).and().csrf().disable().httpBasic();
		//http.formLogin().successForwardUrl("/home/loginsuccess").failureUrl("/home/loginfail");
		// http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
		/*
		 * .requiresChannel() .antMatchers("/*") .requiresSecure() .and()
		 */
		// 谷歌游览器测试中，如果不关闭游览器，到了过期时间也不会重新登录，关闭后有效。
		// .rememberMe().key("lovejj1994").tokenValiditySeconds(60)
		// .and()
		// .logout().logoutSuccessUrl("/").and()
		// .sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());
		/*
		 * .and() .requiresChannel() .antMatchers("/redirect*","/")
		 * .requiresInsecure();
		 */
	}

	// 解决跨域问题
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
		configuration.setAllowedHeaders(Arrays.asList("content-type","authorization","x-auth-token"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	// spring-jpa-redis
//	@Bean
//	public JedisConnectionFactory connectionFactory() {
//		JedisConnectionFactory jcf = new JedisConnectionFactory();
//		jcf.setHostName("127.0.0.1");
//		return jcf;
//	}

	

}
