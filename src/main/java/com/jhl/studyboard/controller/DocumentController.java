package com.jhl.studyboard.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhl.studyboard.dto.DocumentDTO;
import com.jhl.studyboard.entity.Document;
import com.jhl.studyboard.resources.DocumentResource;
import com.jhl.studyboard.resources.ErrorsResource;
import com.jhl.studyboard.service.DocumentService;
import com.jhl.studyboard.utils.HashTagUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/document", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class DocumentController {

	private static final int LIST_SIZE = 10;
	
	@Autowired
	private DocumentService documentService;
	
	@GetMapping
	public ResponseEntity<?> goList(
			@PageableDefault(page = 0, size = LIST_SIZE, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			PagedResourcesAssembler<DocumentDTO> assembler) {
		log.info("rest api call - GET / [" + pageable.toString() + "]");
		
		Page<DocumentDTO> result = documentService.selectList(pageable);

		PagedResources<ResourceSupport> pagedResources = assembler.toResource(result, e -> new DocumentResource(e));
//		pagedResources.add(new Link("").withRel("profile"));
		return ResponseEntity.ok(pagedResources);
	}

	@PostMapping
	public ResponseEntity<?> addDocument(@RequestBody @Valid DocumentDTO documentDto, Errors errors) {
		log.info("rest api call - POST /");
		
		if (errors.hasErrors()) {
			return badRequest(errors);
		}
		
		HashTagUtil.extractHashTag(documentDto);
		Document newDocument = documentService.insert(documentDto);
		documentDto.setId(newDocument.getId());

		ControllerLinkBuilder selfLinkBuilder = linkTo(DocumentController.class).slash(newDocument.getId());
		URI createdUri = selfLinkBuilder.toUri();
		DocumentResource documentResource = new DocumentResource(documentDto);
		documentResource.add(linkTo(DocumentController.class).withRel("list"));
		documentResource.add(selfLinkBuilder.withRel("update"));
//		documentResource.add(new Link("").withRel("profile"));
		return ResponseEntity.created(createdUri).body(documentResource);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editDocument(@PathVariable("id") Long id,
			@RequestBody @Valid DocumentDTO documentDto, Errors errors) {
		log.info("rest api call - PUT /"+id);
		
		if (errors.hasErrors()) {
			return badRequest(errors);
		}
		
		if(documentDto.getId() != id) {
			return ResponseEntity.badRequest().build();
		}

		HashTagUtil.extractHashTag(documentDto);
		documentService.update(documentDto);
		
		DocumentResource documentResource = new DocumentResource(documentDto);
//		documentResource.add(new Link("").withRel("profile"));
		return ResponseEntity.ok(documentResource);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDocument(@PathVariable("id") Long id) {
		log.info("rest api call - DELETE /"+id);
		
		documentService.delete(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> goShow(@PathVariable("id") Long id) {
		log.info("rest api call - GET /"+id);
		
		DocumentDTO documentDto = documentService.select(id);
		
		if(documentDto == null) {
			return ResponseEntity.notFound().build();
		}
		
		DocumentResource documentResource = new DocumentResource(documentDto);
//		documentResource.add(new Link("").withRel("profile"));
		documentResource.add(linkTo(DocumentController.class).slash(documentDto.getId()).withRel("update-document"));
		return ResponseEntity.ok(documentResource);
	}
	
	private ResponseEntity<?> badRequest(Errors errors) {
		return ResponseEntity.badRequest().body(new ErrorsResource(errors));
	}
}
