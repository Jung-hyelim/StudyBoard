package com.jhl.studyboard.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tag")
@Getter
@NoArgsConstructor
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "tag")
	private List<DocumentAndTag> mappings = new ArrayList<DocumentAndTag>();
	
	public Tag(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
