package com.jhl.studyboard.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.validation.Errors;

import com.jhl.studyboard.controller.DocumentController;

public class ErrorsResource extends Resource<Errors> {

	public ErrorsResource(Errors content, Link... links) {
		super(content, links);
		add(linkTo(methodOn(DocumentController.class)).withSelfRel());
	}
}
