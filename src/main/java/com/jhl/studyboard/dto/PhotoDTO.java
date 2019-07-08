package com.jhl.studyboard.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO {

	private String file_path;
	private String file_name;
	private List<PhotoTextDTO> photo_texts = new ArrayList<PhotoTextDTO>();
	
	public PhotoDTO(String file_path, String file_name) {
		this.file_path = file_path;
		this.file_name = file_name;
	}
	
	public void addText(PhotoTextDTO photo_text) {
		this.photo_texts.add(photo_text);
	}
}
