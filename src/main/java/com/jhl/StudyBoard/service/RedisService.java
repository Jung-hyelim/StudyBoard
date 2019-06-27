package com.jhl.StudyBoard.service;

import java.time.Duration;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.jhl.StudyBoard.dto.DocumentDTO;

@Slf4j
@Service
public class RedisService {
	
	private static final String REDIS_KEY_FREFIX = "documentDTO:";

	@Value("${spring.redis.expireTime}")
	private long redisExpireTime;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String, DocumentDTO> valuesOperations;
	
	public void setRedis(Long id, DocumentDTO document) {
		log.debug("set redis data [id:{}, documentDTO:{}]", id, document.toString());
		valuesOperations.set(REDIS_KEY_FREFIX + String.valueOf(id), document, Duration.ofSeconds(redisExpireTime));
	}
	
	public DocumentDTO getRedis(Long id) {
		log.debug("get data from redis [id:{}]", id);
		DocumentDTO document = valuesOperations.get(REDIS_KEY_FREFIX + String.valueOf(id));
		return document;
	}
}
