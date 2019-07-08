package com.jhl.studyboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhl.studyboard.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	
}
