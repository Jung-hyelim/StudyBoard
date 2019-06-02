package com.jhl.StudyBoard.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.repository.DocumentRepository;
import com.jhl.StudyBoard.service.DocumentService;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	@Override
	public void insert(Document document) {
		documentRepository.save(document);
	}
	
	@Override
	public List<Document> findAll() {
		List<Document> list = documentRepository.findAll(Sort.by("id").descending());
		return list;
	}
	
	@Override
	public Optional<Document> findById(long id) {
		return documentRepository.findById(id);
	}
	
	@Override
	public Document update(Document document, long id) {
		return documentRepository.save(document);
	}
	
	@Override
	public void delete(long id) {
		documentRepository.deleteById(id);
	}
}
