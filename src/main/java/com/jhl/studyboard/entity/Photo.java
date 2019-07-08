package com.jhl.studyboard.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "photo")
@Getter
@NoArgsConstructor
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "document_id")
	private Document document;

	@Column
	private String file_path;
	
	@Column
	private String file_name;
	
	@OneToMany(mappedBy = "photo", 
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private List<PhotoText> photo_texts = new ArrayList<PhotoText>();
	
	public Photo(Long id, Document document, String file_path, String file_name) {
		this.id = id;
		this.document = document;
		this.file_path = file_path;
		this.file_name = file_name;
	}
	
	public void setDocument(Document document) {
		this.document = document;
	}

	public void addText(PhotoText photoText) {
		this.photo_texts.add(photoText);
	}
}
