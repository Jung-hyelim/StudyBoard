package com.jhl.studyboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.jhl.studyboard.dto.DocumentDTO;
import com.jhl.studyboard.dto.DocumentListDTO;
import com.jhl.studyboard.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventListener {

	@Autowired
	private RedisService redisService;

	@TransactionalEventListener
	public void handle(DocumentDTO documentDto) {
		log.debug("@TransactionalEventListener > set redis DocumentDTO [id:{}]", documentDto.getId());
		redisService.setDocumentRedis(documentDto.getId(), documentDto);
	}
	
	@TransactionalEventListener
	public void handleList(DocumentListDTO list) {
		log.debug("@TransactionalEventListener > set redis DocumentListDTO [page:{}]", list.getPage());
		redisService.setDocumentListRedis(list.getPage(), list);
	}
}
