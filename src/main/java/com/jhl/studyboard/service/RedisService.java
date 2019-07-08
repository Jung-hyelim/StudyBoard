package com.jhl.studyboard.service;

import java.time.Duration;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.jhl.studyboard.dto.DocumentDTO;
import com.jhl.studyboard.dto.DocumentListDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisService {

	private static final String DOCUMENT_REDIS_KEY_FREFIX = "documentDTO:";
	private static final String LIST_REDIS_KEY_FREFIX = "documentList:";

	@Value("${spring.redis.expireTime}")
	private long redisExpireTime;

	@Resource(name="redisTemplate")
	private ValueOperations<String, DocumentDTO> redisDocument;
	
	@Resource(name="redisTemplate-list")
	private ValueOperations<String, DocumentListDTO> redisDocumentList;
	
	public void setDocumentRedis(Long id, DocumentDTO document) {
		log.debug("set redis data [id:{}, documentDTO:{}]", id, document.toString());
		redisDocument.set(DOCUMENT_REDIS_KEY_FREFIX + String.valueOf(id), document, Duration.ofSeconds(redisExpireTime));
	}
	
	public DocumentDTO getDocumentRedis(Long id) {
		log.debug("get data from redis [id:{}]", id);
		DocumentDTO document = redisDocument.get(DOCUMENT_REDIS_KEY_FREFIX + String.valueOf(id));
		return document;
	}
	
	public void setDocumentListRedis(int page, DocumentListDTO list) {
		log.debug("set redis list [page:{}, list:{}]", page, list.toString());
		redisDocumentList.set(LIST_REDIS_KEY_FREFIX + page, list, Duration.ofSeconds(redisExpireTime));
	}
	
	public DocumentListDTO getDocumentListRedis(int page) {
		log.debug("get list from redis [page:{}]", page);
		DocumentListDTO list = redisDocumentList.get(LIST_REDIS_KEY_FREFIX + page);
		return list;
	}
	
}
