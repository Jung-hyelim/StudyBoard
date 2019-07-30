package com.jhl.studyboard.service;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhl.studyboard.dto.DocumentDTO;
import com.jhl.studyboard.entity.Document;
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
	
	@Autowired
	private KafkaTemplate<String, Long> kafkaTemplate;

	@Transactional(readOnly=false)
	public Document insert(DocumentDTO documentDto) {
		Document document = new Document(documentDto);
		this.settingSelectedTags(document);
		return documentRepository.save(document);
	}
	
	@Transactional(readOnly=true)
	public Page<DocumentDTO> selectList(int page, int size) {
		Page<Long> ids = documentRepository.findAllOnlyId(PageRequest.of(page, size, Direction.DESC, "id"));
		
		List<String> keys = ids.stream().map(id -> DocumentDTO.REDIS_KEY_PREFIX + id).collect(toList());
		List<DocumentDTO> dtoList = redisDocument.multiGet(keys).stream().filter(dto -> dto != null).collect(toList());
		log.debug("redis multi get [keys:{}]", keys.toString());

		List<Long> notNullIds = dtoList.stream().map(dto -> dto.getId()).collect(toList());
		List<Long> nullIds = ids.getContent().stream().filter(id -> !notNullIds.contains(id)).collect(toList());
		
		if(nullIds.size() > 0) {
			nullIds.stream().forEach(id -> {
				DocumentDTO dto = this.selectAndEventPulish(id);
				dtoList.add(dto);
			});
			
		}
		List<DocumentDTO> resultList = dtoList.stream().sorted(Comparator.comparing(DocumentDTO::getId).reversed()).collect(toList());
		
		Page<DocumentDTO> result = new PageImpl<DocumentDTO>(resultList, ids.getPageable(), ids.getTotalElements());
		return result;
	}
	
	@Transactional(readOnly=true)
	public DocumentDTO select(long id) {
		kafkaTemplate.send("document-read", id);
		
		log.debug("get documentDTO from redis [id:{}]", id);
		DocumentDTO documentDto = redisDocument.get(DocumentDTO.REDIS_KEY_PREFIX + String.valueOf(id));
		
		if(documentDto == null) {
			documentDto = this.selectAndEventPulish(id);
		}

		return documentDto;
	}
	
	@Transactional(readOnly=false)
	public Document update(DocumentDTO changeDocumentDto) {
		Document document = documentRepository.findById(changeDocumentDto.getId()).orElseThrow(() -> new DocumentNotFoundException("no data in update"));

		Document changeDocument = new Document(changeDocumentDto);
		this.settingSelectedTags(changeDocument);
		
		document.update(changeDocument);
		return document;
	}
	
	@Transactional(readOnly=false)
	public void delete(long id) {
		documentRepository.deleteById(id);
	}

	private DocumentDTO selectAndEventPulish(long id) {
		log.debug("find by id({}) and publish event", id);
		
		Document document = documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException("no data in findById"));
		document.getPhotos().stream().forEach(p -> p.getPhoto_texts());
		document.getMappings();
		
		DocumentDTO documentDto = new DocumentDTO(document);
		
		// Document Event publish
		publisher.publishEvent(documentDto);
		
		return documentDto;
	}
	
	private void settingSelectedTags(Document document) {
		// Tag가 기존에 있는지 조회 후, 조회된 Tag로 셋팅
		document.getMappings().stream().forEach(m -> 
			tagRepository.findByName(m.getTag().getName()).stream().forEach(t -> m.setTag(t))
		);
	}
}
