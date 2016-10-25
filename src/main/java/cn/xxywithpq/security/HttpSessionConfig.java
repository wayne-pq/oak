package cn.xxywithpq.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}
//	@Autowired
//	private FindByIndexNameSessionRepository<ExpiringSession> sessionRepository;
//	
//	@Bean
//	public JedisConnectionFactory connectionFactory() {
//		JedisConnectionFactory jcf = new JedisConnectionFactory();
//		jcf.setHostName("127.0.0.1");
//		return jcf;
//	}
//	
//	@Bean
//	public FindByIndexNameSessionRepository<?> sessionRepository() {
//		return new RedisOperationsSessionRepository(connectionFactory());
//	}
//
//	@Bean
//	public SpringSessionBackedSessionRegistry sessionRegistry() {
//		return new SpringSessionBackedSessionRegistry(this.sessionRepository);
//	}
}