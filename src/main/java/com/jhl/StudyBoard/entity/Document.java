package com.jhl.StudyBoard.entity;

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
import lombok.ToString;

@Entity
@Table(name = "document")
@Getter
@ToString
@NoArgsConstructor
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(length = 50)
	private String title;
	
	@Column
	private String content;
	
	@OneToMany(mappedBy = "document", 
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private List<Photo> photos = new ArrayList<Photo>();
	
	public Document(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	public void update(Document document) {
		this.title = document.title;
		this.content = document.content;

		// photos
		this.photos.clear();
		document.getPhotos().stream().forEach(p -> p.setDocument(this));
		this.photos.addAll(document.getPhotos());
	}
	
	public void addPhoto(Photo photo) {
		this.photos.add(photo);
	}
}
