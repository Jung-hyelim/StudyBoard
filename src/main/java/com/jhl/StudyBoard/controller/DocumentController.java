package com.jhl.StudyBoard.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.repository.DocumentRepository;
import com.jhl.StudyBoard.service.DocumentService;

@Controller
@RequestMapping("/")
public class DocumentController {
	
	private final int PAGE_SIZE = 3;
	private final String REDIRECT_MAPPING = "redirect:/";

	//@Autowired
	//private DocumentService documentService;
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@GetMapping("")
	public String getDocuments(Model model,
			@PageableDefault(sort = { "id" }, direction = Direction.DESC, size = PAGE_SIZE, page = 0) Pageable pageable) {
		Page<Document> result = documentRepository.findAll(pageable);
		model.addAttribute("page", result);
		return "list";
	}
	
	@GetMapping("/{id}")
	public String showDocuments(Model model, @PathVariable("id") Long id) {
		Optional<Document> document = documentRepository.findById(id);
		model.addAttribute("document", document.get());
		return "detail";
	}
	
	@GetMapping("/new")
	public String newDocuments(Model model) {
		return "new";
	}

	@GetMapping("/edit/{id}")
	public String editDocuments(Model model, @PathVariable("id") Long id) {
		Optional<Document> document = documentRepository.findById(id);
		model.addAttribute("document", document.get());
		return "edit";
	}

	@PostMapping("")
	public String addDocuments(Document document) {
		documentRepository.save(document);
		return REDIRECT_MAPPING;
	}
	
	@PutMapping("/{id}")
	public String updateDocuments(Document document, @PathVariable("id") Long id) {
		documentRepository.save(document);
		return REDIRECT_MAPPING+id;
	}
	
	@DeleteMapping("/{id}")
	public String deleteDocuments(@PathVariable("id") Long id) {
		documentRepository.deleteById(id);
		return REDIRECT_MAPPING;
	}
	
}
