package com.jhl.studyboard.listener;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.jhl.studyboard.dto.DocumentDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DocumentEventListener {

	@Resource(name="redisTemplate")
	private ValueOperations<String, DocumentDTO> redisDocument;
	
	@Value("${spring.redis.expire-time}")
	private long redisExpireTime;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleDto(DocumentDTO documentDto) {
		log.debug("TransactionalEventListener > set redis DocumentDTO [id:{}]", documentDto.getId());
		redisDocument.set(documentDto.redisKey(), documentDto, redisExpireTime, TimeUnit.SECONDS);
	}
	
}
