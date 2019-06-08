package com.jhl.StudyBoard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "photo")
@Getter
@ToString
@NoArgsConstructor
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, 
			optional = false)
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
