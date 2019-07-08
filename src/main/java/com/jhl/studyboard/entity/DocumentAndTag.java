package com.jhl.studyboard.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document_and_tag")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentAndTag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "document_id")
	private Document document;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tag_id")
	private Tag tag;
	
	public void setDocument(Document document) {
		this.document = document;
	}
	
	public void setTag(Tag tag) {
		this.tag = tag;
	}
}
