package cn.xxywithpq.config;

import java.util.Properties;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import cn.xxywithpq.service.MessageDelegate;

/**
 * 一些配置项
 * 
 * @author panqian
 * @date 2016年11月24日 下午11:52:41
 */
@Configuration
public class SpringConfig {

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;

	@Autowired
	private CachingConnectionFactory cachingConnectionFactory;

	// spring-jpa-redis
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> rt = new RedisTemplate<String, Object>();
		rt.setConnectionFactory(jedisConnectionFactory);
		rt.setKeySerializer(new StringRedisSerializer());
		return rt;
	}

	// rabbitmq

	@Bean
	public org.springframework.amqp.support.converter.MessageConverter rabbitMessageConverter() {
		Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
		return converter;
	}

	@Bean
	public Queue rabbitQueue() {
		return new Queue("test.rabbit.queue");
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("test.rabbit.direct");
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(rabbitQueue()).to(directExchange()).with("test.rabbit.binding");
	}

	@Bean(name = "myRabbitAdmin")
	public RabbitAdmin admin() {
		RabbitAdmin admin = new RabbitAdmin(cachingConnectionFactory);
		admin.declareQueue(rabbitQueue());
		admin.declareExchange(directExchange());
		admin.declareBinding(binding());
		return admin;
	}

	@Bean(name = "myRabbitTemplate")
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
		rabbitTemplate.setMessageConverter(rabbitMessageConverter());
		return rabbitTemplate;
	}

	// 监听器（适配器）
	@Bean(name = "rabbitMessageListenerAdapter")
	public org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter rabbitMessageListenerAdapter(
			MessageDelegate handler) {
		org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter adapter = new org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter(
				handler);
		adapter.setDefaultListenerMethod("handleRabbitMsg");
		adapter.setMessageConverter(rabbitMessageConverter());
		return adapter;
	}

	// 监听容器
	@Bean
	public SimpleMessageListenerContainer rabbitlistenerContainer(
			org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter rabbitMessageListenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(cachingConnectionFactory);
		container.setMessageListener(rabbitMessageListenerAdapter);
		container.setQueueNames("test.rabbit.queue");
		return container;
	}

	// spring-mail
	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
		javaMail.setHost("smtp.qq.com");
		javaMail.setPort(465);
		javaMail.setUsername("455234037@qq.com");
		javaMail.setPassword("zzsgobnvmvrybjde");
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		javaMail.setJavaMailProperties(properties);
		return javaMail;
	}


}
