package com.jhl.studyboard.listener;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jhl.studyboard.repository.DocumentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DocumentReadListener {

	@Autowired
	private DocumentRepository documentRepository;

	@Transactional(readOnly = false)
	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(Long id) throws IOException {
		log.info("Kafka Consumed id -> [id:" + id.toString() + "]");
		documentRepository.updateReadCount(id, 1);
	}
}
