package com.jhl.studyboard.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jhl.studyboard.entity.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DocumentListDTO {

	List<DocumentListItemDTO> list = new ArrayList<DocumentListItemDTO>();
	private int totalPages;
	private int page;

	public DocumentListDTO(Page<Document> document) {
		document.getContent().stream().forEach(d -> this.list.add(new DocumentListItemDTO(d)));
		this.totalPages = document.getTotalPages();
		this.page = document.getNumber();
	}
}
