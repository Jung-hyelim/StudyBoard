package com.jhl.studyboard.dto;

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
}
