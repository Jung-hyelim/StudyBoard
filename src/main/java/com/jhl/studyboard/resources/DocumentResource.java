package com.jhl.studyboard.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import com.jhl.studyboard.controller.DocumentController;
import com.jhl.studyboard.dto.DocumentDTO;

public class DocumentResource extends Resource<DocumentDTO> {

	public DocumentResource(DocumentDTO documentDto, Link... links) {
		super(documentDto, links);
		add(linkTo(DocumentController.class).slash(documentDto.getId()).withSelfRel());
	}

}
