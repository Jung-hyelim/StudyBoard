package com.jhl.studyboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhl.studyboard.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
	
}
