package com.jhl.studyboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jhl.studyboard.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	@Query(value = "select d.id from Document d",
			countQuery = "select count(d.id) from Document d")
	Page<Long> findAllOnlyId(Pageable pageable);
	
}
