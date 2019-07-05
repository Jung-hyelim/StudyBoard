package com.jhl.StudyBoard.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhl.StudyBoard.dto.DocumentDTO;
import com.jhl.StudyBoard.dto.DocumentListDTO;
import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.entity.Tag;
import com.jhl.StudyBoard.exception.DocumentNotFoundException;
import com.jhl.StudyBoard.repository.DocumentRepository;
import com.jhl.StudyBoard.repository.TagRepository;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@Transactional(readOnly=false)
	public Document insert(DocumentDTO documentDto) {
		Document document = new Document();
		document.setFromDto(documentDto);
		
		// Tag가 기존에 있는지 조회 후, 조회된 Tag로 셋팅
		document.getMappings().stream().forEach(m -> {
			List<Tag> findTag = tagRepository.findByName(m.getTag().getName());
			findTag.stream().forEach(t -> m.setTag(t));
		});
		
		return documentRepository.save(document);
	}
	
	@Transactional(readOnly=true)
	public DocumentListDTO selectList(int page, int size) {
		Page<Document> document = documentRepository.findAll(new PageRequest(page, size, Direction.DESC, "id"));
		return new DocumentListDTO(document);
	}
	
	@Transactional(readOnly=true)
	public DocumentDTO select(long id) {
		Document document = documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException("no data in findById"));
		document.getPhotos().stream().forEach(p -> Hibernate.initialize(p.getPhoto_texts()));
		Hibernate.initialize(document.getMappings());
		
		// DTO
		DocumentDTO dto = new DocumentDTO(document);
		return dto;
	}
	
	@Transactional(readOnly=false)
	public Document update(DocumentDTO changeDocumentDto) {
		Document document = documentRepository.findById(changeDocumentDto.getId()).orElseThrow(() -> new DocumentNotFoundException("no data in update"));

		Document changeDocument = new Document();
		changeDocument.setFromDto(changeDocumentDto);
		
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
