package com.jhl.studyboard.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import com.jhl.studyboard.dto.DocumentDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document")
@Getter
@NoArgsConstructor
@DynamicInsert
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50)
	private String title;
	
	@Column
	private String content;
	
	@Column
	@CreationTimestamp
	private LocalDateTime create_date;
	 
	@Column
	@UpdateTimestamp
	private LocalDateTime update_date;

	@Column @ColumnDefault(value = "0")
	private Integer read_count;
	
	@OneToMany(mappedBy = "document", 
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private List<Photo> photos = new ArrayList<Photo>();

	@OneToMany(mappedBy = "document",
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private List<DocumentAndTag> mappings = new ArrayList<DocumentAndTag>();
	
	public Document(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	public void update(Document document) {
		this.title = document.title;
		this.content = document.content;

		// clear
		this.photos.clear();
		this.mappings.clear();
		
		// photos
		document.getPhotos().stream().forEach(p -> {
			p.setDocument(this);
			this.photos.add(p);
		});
		
		// tag mapping
		document.getMappings().stream().forEach(m -> m.setDocument(this));
		this.mappings.addAll(document.getMappings());
	}
	
	public void addPhoto(Photo photo) {
		this.photos.add(photo);
	}
	
	public void setMappings(List<DocumentAndTag> documentAndTag) {
		this.mappings.addAll(documentAndTag);
	}
	
	public Document(DocumentDTO dto) {
		this.id = dto.getId();
		this.title = dto.getTitle();
		this.content = dto.getContent();
		this.read_count = dto.getRead_count();
		this.photos = dto.getPhotos().stream().map(Photo::new).collect(Collectors.toList());
		this.photos.stream().forEach(photo -> photo.setDocument(this));
		this.mappings = dto.getTags().stream().map(DocumentAndTag::new).collect(Collectors.toList());
		this.mappings.stream().forEach(mapping -> mapping.setDocument(this));
	}
}
