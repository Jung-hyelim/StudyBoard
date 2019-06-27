package com.jhl.StudyBoard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

import com.jhl.StudyBoard.dto.DocumentDTO;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private int redisPort;
	
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxWaitMillis(1000);
		return jedisPoolConfig;
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public JedisConnectionFactory connectionFactory() {	
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
		jedisConnectionFactory.setHostName(redisHost);
		jedisConnectionFactory.setPort(redisPort);
		jedisConnectionFactory.setUsePool(true);
		return jedisConnectionFactory;
	}
	
	@Bean(name="redisTemplate")
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<DocumentDTO>(DocumentDTO.class));
		redisTemplate.setEnableDefaultSerializer(false);
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}
}
