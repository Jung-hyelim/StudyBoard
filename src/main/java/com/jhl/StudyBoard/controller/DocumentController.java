package com.jhl.StudyBoard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhl.StudyBoard.dto.DocumentDTO;
import com.jhl.StudyBoard.dto.DocumentListDTO;
import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.service.DocumentService;

@Api(description = "문서 API")
@RestController
@RequestMapping("/api/document")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;
	
	@ApiOperation(value = "문서 리스트")
	@GetMapping("")
	public DocumentListDTO getDocuments(@RequestParam(value="page", defaultValue="0") int page, 
										@RequestParam(value="size", required=false, defaultValue="10") int size) {
		DocumentListDTO result = documentService.selectList(page, size);
		return result;
	}

	@ApiOperation(value = "문서 상세 정보")
	@GetMapping("/{id}")
	public DocumentDTO showDocuments(@PathVariable("id") Long id) {
		DocumentDTO documentDto = documentService.select(id);
		return documentDto;
	}

	@ApiOperation(value = "문서 저장")
	@PostMapping("")
	public Document addDocuments(DocumentDTO document) {
		Document saved = documentService.insert(document);
		return saved;
	}

	@ApiOperation(value = "문서 수정")
	@PutMapping("/{id}")
	public Document updateDocuments(DocumentDTO document, @PathVariable("id") Long id) {
		Document saved = documentService.update(document);
		return saved;
	}

	@ApiOperation(value = "문서 삭제")
	@DeleteMapping("/{id}")
	public void deleteDocuments(@PathVariable("id") Long id) {
		documentService.delete(id);
	}
	
}
