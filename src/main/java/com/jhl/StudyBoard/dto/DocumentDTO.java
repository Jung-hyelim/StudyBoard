package com.jhl.StudyBoard.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jhl.StudyBoard.entity.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class DocumentDTO {

	private Long id;
	private String title;
	private String content;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	private List<PhotoDTO> photos = new ArrayList<PhotoDTO>();
	private List<TagDTO> tags = new ArrayList<TagDTO>();
	private int photos_size;
	private int tags_size;

	public void addPhoto(PhotoDTO photo) {
		this.photos.add(photo);
	}
	public void addTag(TagDTO tag) {
		this.tags.add(tag);
	}
	
	public void setFromEntity(Document document) {
		this.setId(document.getId());
		this.setTitle(document.getTitle());
		this.setContent(document.getContent());
		this.setCreate_date(document.getCreate_date());
		this.setUpdate_date(document.getUpdate_date());
		this.setPhotos_size(document.getPhotos().size());
		this.setTags_size(document.getMappings().size());
		document.getPhotos().stream().forEach(p -> {
			PhotoDTO photoDto = new PhotoDTO(p.getFile_path(), p.getFile_name());
			p.getPhoto_texts().stream().forEach(t -> photoDto.addText(new PhotoTextDTO(t.getPosition_x(), t.getPosition_y(), t.getText())));
			this.addPhoto(photoDto);
		});
		document.getMappings().stream().forEach(m -> this.addTag(new TagDTO(m.getTag().getName())));
	}
}
