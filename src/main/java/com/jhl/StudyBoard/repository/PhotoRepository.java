package com.jhl.StudyBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhl.StudyBoard.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
	
}
