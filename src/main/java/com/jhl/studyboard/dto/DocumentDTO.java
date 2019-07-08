package com.jhl.studyboard.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jhl.studyboard.entity.Document;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class DocumentDTO {

	private Long id;
	private String title;
	private String content;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime create_date;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime update_date;
	private List<PhotoDTO> photos = new ArrayList<PhotoDTO>();
	private List<TagDTO> tags = new ArrayList<TagDTO>();

	public void addPhoto(PhotoDTO photo) {
		this.photos.add(photo);
	}
	public void addTag(TagDTO tag) {
		this.tags.add(tag);
	}
	
	public DocumentDTO(Document document) {
		this.setId(document.getId());
		this.setTitle(document.getTitle());
		this.setContent(document.getContent());
		this.setCreate_date(document.getCreate_date());
		this.setUpdate_date(document.getUpdate_date());
		document.getPhotos().stream().forEach(p -> {
			PhotoDTO photoDto = new PhotoDTO(p.getFile_path(), p.getFile_name());
			p.getPhoto_texts().stream().forEach(t -> photoDto.addText(new PhotoTextDTO(t.getPosition_x(), t.getPosition_y(), t.getText())));
			this.addPhoto(photoDto);
		});
		document.getMappings().stream().forEach(m -> this.addTag(new TagDTO(m.getTag().getName())));
	}
}
