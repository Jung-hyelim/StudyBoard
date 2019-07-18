package com.jhl.studyboard.dto;

import com.jhl.studyboard.entity.DocumentAndTag;
import com.jhl.studyboard.entity.Tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {

	private Long id;
	private String name;
	
	public TagDTO(String name) {
		this.name = name;
	}
	
	public TagDTO(DocumentAndTag documentAndTag) {
		Tag tag = documentAndTag.getTag();
		this.id = tag.getId();
		this.name = tag.getName();
	}
	
	public TagDTO(TagDTO tag) {
		this.id = tag.getId();
		this.name = tag.getName();
	}
}
