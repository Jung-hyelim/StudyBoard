package com.jhl.studyboard.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.jhl.studyboard.dto.DocumentDTO;

@Entity
@Table(name = "document")
@Getter
@NoArgsConstructor
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
		//this.photos.addAll(document.getPhotos());
		
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
	
	public void setFromDto(DocumentDTO dto) {
		this.id = dto.getId();
		this.title = dto.getTitle();
		this.content = dto.getContent();
		
		dto.getPhotos().stream().forEach(p -> {
			if(p.getFile_name() != null){
				Photo photo = new Photo(null, this, p.getFile_path(), p.getFile_name());
				p.getPhoto_texts().stream().forEach(t -> {
					if(t.getText() != null && !t.getText().equals("")){
						photo.addText(new PhotoText(null, photo, t.getPosition_x(), t.getPosition_y(), t.getText()));
					}
				});
				this.photos.add(photo);
			}
		});
		
		dto.getTags().stream().forEach(t -> {
			this.mappings.add(new DocumentAndTag(null, this, new Tag(null, t.getName())));
		});
	}
}
