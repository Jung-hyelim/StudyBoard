package com.jhl.StudyBoard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.service.DocumentService;

@Controller
@RequestMapping("/documents")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@GetMapping("")
	public String getDocuments(Model model) {
		List<Document> list = documentService.findAll();
		model.addAttribute("list", list);
		return "list";
	}
	
	@GetMapping("/{id}")
	public String showDocuments(Model model, @PathVariable("id") Long id) {
		Optional<Document> document = documentService.findById(id);
		model.addAttribute("document", document.get());
		return "detail";
	}
	
	@GetMapping("/new")
	public String newDocuments(Model model) {
		return "new";
	}

	@GetMapping("/{id}/edit")
	public String editDocuments(Model model, @PathVariable("id") Long id) {
		Optional<Document> document = documentService.findById(id);
		model.addAttribute("document", document.get());
		return "edit";
	}

	@PostMapping("")
	public String addDocuments(Document document) {
		documentService.insert(document);
		return "redirect:/documents";
	}
	
	@PutMapping("/{id}")
	public String updateDocuments(Document document, @PathVariable("id") Long id) {
		documentService.update(document, id);
		return "redirect:/documents/"+id;
	}
	
	@DeleteMapping("/{id}")
	public String deleteDocuments(@PathVariable("id") Long id) {
		documentService.delete(id);
		return "redirect:/documents";
	}
}
