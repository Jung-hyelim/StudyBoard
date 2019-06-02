package com.jhl.StudyBoard.service;

import java.util.List;
import java.util.Optional;

import com.jhl.StudyBoard.entity.Document;

public interface DocumentService {

	public void insert(Document document);
	
	public List<Document> findAll();
	
	public Optional<Document> findById(long id);
	
	public Document update(Document document, long id);
	
	public void delete(long id);
	
}
