package com.jhl.StudyBoard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.jhl.StudyBoard.dto.DocumentDTO;
import com.jhl.StudyBoard.dto.DocumentListDTO;
import com.jhl.StudyBoard.service.RedisService;

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
