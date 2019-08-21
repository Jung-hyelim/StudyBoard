package com.jhl.studyboard.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jhl.studyboard.entity.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class DocumentDTO {
	public static final String REDIS_KEY_PREFIX = "documentDTO:";

	private Long id;
	@NotNull private String title;
	@NotNull private String content;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime create_date;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime update_date;
	private Integer read_count;
	private List<PhotoDTO> photos = new ArrayList<PhotoDTO>();
	private List<TagDTO> tags = new ArrayList<TagDTO>();

	public void addPhoto(PhotoDTO photo) {
		this.photos.add(photo);
	}
	public void addTag(TagDTO tag) {
		this.tags.add(tag);
	}
	
	public DocumentDTO(Document document) {
		this.id = document.getId();
		this.title = document.getTitle();
		this.content = document.getContent();
		this.create_date = document.getCreate_date();
		this.update_date = document.getUpdate_date();
		this.read_count = document.getRead_count();
		this.photos = document.getPhotos().stream().map(PhotoDTO::new).collect(Collectors.toList());
		this.tags = document.getMappings().stream().map(TagDTO::new).collect(Collectors.toList());
	}
	
	public DocumentDTO(DocumentDTO document) {
		this.id = document.getId();
		this.title = document.getTitle();
		this.content = document.getContent();
		this.create_date = document.getCreate_date();
		this.update_date = document.getUpdate_date();
		this.read_count = document.getRead_count();
		this.photos = document.getPhotos().stream().map(PhotoDTO::new).collect(Collectors.toList());
		this.tags = document.getTags().stream().map(TagDTO::new).collect(Collectors.toList());
	}
	
	public String redisKey() {
		return REDIS_KEY_PREFIX + this.id;
	}
}
