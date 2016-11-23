package cn.xxywithpq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * 一些配置项
 * @author panqian
 * @date 2016年11月24日 下午11:52:41
 */
@Configuration
public class SpringConfig {

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;

	// spring-jpa-redis
	@Bean
	public RedisTemplate redisTemplate() {
		RedisTemplate rt = new RedisTemplate();
		rt.setConnectionFactory(jedisConnectionFactory);
		rt.setKeySerializer(new StringRedisSerializer());
		return rt;
	}

}
