package com.jhl.StudyBoard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		List<Document> list = documentService.getDocuments();
		model.addAttribute("list", list);
		return "list";
	}
	
	@GetMapping("/{id}")
	public String showDocuments(Model model, @PathVariable("id") Long id) {
		Optional<Document> document = documentService.findById(id);
		model.addAttribute("document", document);
		return "detail";
	}
}
