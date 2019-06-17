package com.jhl.StudyBoard.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jhl.StudyBoard.entity.Document;

@Getter
@Setter
@NoArgsConstructor
public class DocumentListDTO {

	List<Document> list = new ArrayList<Document>();
	private int totalPages;
	private int page;

	public DocumentListDTO(Page<Document> document) {
		this.list = document.getContent();
		this.totalPages = document.getTotalPages();
		this.page = document.getNumber();
	}
}
