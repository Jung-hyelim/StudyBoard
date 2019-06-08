package com.jhl.StudyBoard.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jhl.StudyBoard.entity.Document;

public interface DocumentService {

	public Document insert(Document document);
	
	public Page<Document> findAll(Pageable pageable);
	
	public Document findById(long id);
	
	public Document update(Document document);
	
	public void delete(long id);
	
}
