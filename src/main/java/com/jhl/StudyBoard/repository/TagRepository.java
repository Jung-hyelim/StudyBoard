package com.jhl.StudyBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhl.StudyBoard.entity.Tag;
import java.lang.String;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

	List<Tag> findByName(String name);
}
