package com.jhl.StudyBoard.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jhl.StudyBoard.entity.Photo;
import com.jhl.StudyBoard.entity.Tag;

@Getter
@Setter
@NoArgsConstructor
public class DocumentDTO {

	private Long id;
	private String title;
	private String content;
	private List<Photo> photos;
	private List<Tag> tags;

	public void addTag(Tag tag) {
		if(tags == null){
			tags = new ArrayList<Tag>();
		}
		this.tags.add(tag);
	}
}
