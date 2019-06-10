package com.jhl.StudyBoard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	public Photo(Long id, Document document, String file_path, String file_name) {
		this.id = id;
		this.document = document;
		this.file_path = file_path;
		this.file_name = file_name;
	}
	
	public void setDocument(Document document) {
		this.document = document;
	}
}
