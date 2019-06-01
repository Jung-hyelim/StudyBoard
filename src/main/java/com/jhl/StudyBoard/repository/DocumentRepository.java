package com.jhl.StudyBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhl.StudyBoard.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
