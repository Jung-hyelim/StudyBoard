package com.jhl.StudyBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.exception.DocumentNotFoundException;
import com.jhl.StudyBoard.repository.DocumentRepository;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Transactional(readOnly=false)
	public Document insert(Document document) {
		return documentRepository.save(document);
	}
	
	@Transactional(readOnly=true)
	public Page<Document> findAll(Pageable pageable) {
		return documentRepository.findAll(pageable);
	}
	
	@Transactional(readOnly=true)
	public Document findById(long id) {
		Document document = documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException("no data in findById"));
		document.getPhotos().size();
		//Hibernate.initialize(document.getPhotos());
		return document;
	}
	
	@Transactional(readOnly=false)
	public Document update(Document changeDocument) {
		Document document = documentRepository.findById(changeDocument.getId()).orElseThrow(() -> new DocumentNotFoundException("no data in update"));
		document.update(changeDocument);
		return document;
	}
	
	@Transactional(readOnly=false)
	public void delete(long id) {
		documentRepository.deleteById(id);
	}
}
