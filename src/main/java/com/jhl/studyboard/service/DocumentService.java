package com.jhl.studyboard.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhl.studyboard.config.RedisConfig;
import com.jhl.studyboard.dto.DocumentDTO;
import com.jhl.studyboard.entity.Document;
import com.jhl.studyboard.entity.Tag;
import com.jhl.studyboard.exception.DocumentNotFoundException;
import com.jhl.studyboard.repository.DocumentRepository;
import com.jhl.studyboard.repository.TagRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, DocumentDTO> redisDocument;
	
	@Transactional(readOnly=false)
	public Document insert(DocumentDTO documentDto) {
		Document document = new Document(documentDto);
		
		// Tag가 기존에 있는지 조회 후, 조회된 Tag로 셋팅
		document.getMappings().stream().forEach(m -> {
			List<Tag> findTag = tagRepository.findByName(m.getTag().getName());
			findTag.stream().forEach(t -> m.setTag(t));
		});
		
		return documentRepository.save(document);
	}
	
	@Transactional(readOnly=true)
	public Page<Document> selectList(int page, int size) {
		Page<Document> list = documentRepository.findAll(PageRequest.of(page, size, Direction.DESC, "id"));
		return list;
	}
	
	@Transactional(readOnly=true)
	public DocumentDTO select(long id) {
		DocumentDTO documentDto = redisDocument.get(RedisConfig.DOCUMENT_KEY_PREFIX + String.valueOf(id));
		log.debug("get documentDTO from redis [id:{}]", id);
		
		if(documentDto == null) {
			Document document = documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException("no data in findById"));
			document.getPhotos().stream().forEach(p -> Hibernate.initialize(p.getPhoto_texts()));
			Hibernate.initialize(document.getMappings());
			
			// DTO
			documentDto = new DocumentDTO(document);
			
			// Event publish
			publisher.publishEvent(documentDto);
		}
		
		return documentDto;
	}
	
	@Transactional(readOnly=false)
	public Document update(DocumentDTO changeDocumentDto) {
		Document document = documentRepository.findById(changeDocumentDto.getId()).orElseThrow(() -> new DocumentNotFoundException("no data in update"));

		Document changeDocument = new Document(changeDocumentDto);
		
		// Tag가 기존에 있는지 조회 후, 조회된 Tag로 셋팅
		changeDocument.getMappings().stream().forEach(m -> {
			List<Tag> findTag = tagRepository.findByName(m.getTag().getName());
			findTag.stream().forEach(t -> m.setTag(t));
		});
		
		document.update(changeDocument);
		return document;
	}
	
	@Transactional(readOnly=false)
	public void delete(long id) {
		documentRepository.deleteById(id);
	}
}
