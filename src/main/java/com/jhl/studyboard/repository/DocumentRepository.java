package com.jhl.studyboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jhl.studyboard.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	@Query(value = "select d.id from Document d",
			countQuery = "select count(d.id) from Document d")
	Page<Long> findAllOnlyId(Pageable pageable);
	
	@Modifying
	@Query(value = "update Document d set d.read_count = (d.read_count + :count) where d.id = :id")
	void updateReadCount(@Param(value = "id") Long id, 
						@Param(value = "count") Integer count);
}
