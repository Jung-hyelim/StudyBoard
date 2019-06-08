package com.jhl.StudyBoard.service.impl;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.repository.DocumentRepository;
import com.jhl.StudyBoard.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Override
	@Transactional(readOnly=false)
	public Document insert(Document document) {
		return documentRepository.save(document);
	}
	
	@Override
	@Transactional
	public Page<Document> findAll(Pageable pageable) {
		return documentRepository.findAll(pageable);
	}
	
	@Override
	@Transactional
	public Document findById(long id) {
		Document document = documentRepository.findById(id).orElseThrow(() -> new IllegalIdentifierException("no data"));
		document.getPhotos();
		return document;
	}
	
	@Override
	@Transactional(readOnly=false)
	public Document update(Document changeDocument) {
		Document document = documentRepository.findById(changeDocument.getId()).orElseThrow(() -> new IllegalIdentifierException("no data"));
		document.update(changeDocument);
		return document;
	}
	
	@Override
	@Transactional(readOnly=false)
	public void delete(long id) {
		documentRepository.deleteById(id);
	}
}
