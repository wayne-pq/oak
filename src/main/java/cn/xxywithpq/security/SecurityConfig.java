package cn.xxywithpq.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	//
	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http mapsto https
	/*
	 * .portMapper().http(8080).mapsTo(8444) .and()
	 */
	// super.configure(http);
	// http.httpBasic();

	// http.cors().and().csrf().disable();
	// http.authorizeRequests().antMatchers("/hadoop/icon/**").permitAll().antMatchers("/login/**").authenticated()
	// .and().requestCache().requestCache(new
	// NullRequestCache()).and().httpBasic();

	// http.csrf().disable()
	// .formLogin()
	// .loginPage("/login")
	// .and()
	// .authorizeRequests()
	// .mvcMatchers(HttpMethod.GET,"/").authenticated()
	// .anyRequest().permitAll()
	// .and()
	// http.authorizeRequests().antMatchers("/home/**").permitAll().antMatchers("/work/**").authenticated()
	// .and().cors().configurationSource(corsConfigurationSource()).and().csrf().disable().httpBasic();
	// http.formLogin().successForwardUrl("/home/loginsuccess").failureUrl("/home/loginfail");
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
	// }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors();
//		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
	}

//	// 解决跨域问题
//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://www.xxywithpq.cn"));
//		configuration.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST"));
//		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "x-auth-token"));
//		configuration.setAllowCredentials(true);
//
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}

}
