package com.jhl.StudyBoard.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jhl.StudyBoard.entity.Document;

public interface DocumentService {

	public void insert(Document document);
	
	public Page<Document> findAll(Pageable pageable);
	
	public Optional<Document> findById(long id);
	
	public Document update(Document document, long id);
	
	public void delete(long id);
	
}
