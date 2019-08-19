package com.jhl.studyboard.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.jhl.studyboard.entity.Photo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO {

	@NotNull private String file_path;
	@NotNull private String file_name;
	private List<PhotoTextDTO> photo_texts = new ArrayList<PhotoTextDTO>();
	
	public PhotoDTO(String file_path, String file_name) {
		this.file_path = file_path;
		this.file_name = file_name;
	}
	
	public PhotoDTO(Photo photo) {
		this.file_path = photo.getFile_path();
		this.file_name = photo.getFile_name();
		this.photo_texts = photo.getPhoto_texts().stream().map(PhotoTextDTO::new).collect(Collectors.toList());
	}
	
	public PhotoDTO(PhotoDTO photo) {
		this.file_path = photo.getFile_path();
		this.file_name = photo.getFile_name();
		this.photo_texts = photo.getPhoto_texts().stream().map(PhotoTextDTO::new).collect(Collectors.toList());
	}
	
	public void addText(PhotoTextDTO photo_text) {
		this.photo_texts.add(photo_text);
	}
}
