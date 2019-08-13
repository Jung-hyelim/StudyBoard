package com.jhl.studyboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhl.studyboard.dto.DocumentDTO;
import com.jhl.studyboard.service.DocumentService;
import com.jhl.studyboard.utils.HashTagUtil;

@Controller
public class DocumentController_tymeleaf {

	@Autowired
	private DocumentService documentService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goList(Model model,
//			@RequestParam(value="page", defaultValue="0") int page,
			Pageable pageable) {
		Page<DocumentDTO> result = documentService.selectList(pageable);
		model.addAttribute("list", result.getContent());
		model.addAttribute("totalPage", result.getTotalPages());
		model.addAttribute("page", result.getNumber());
		return "list";
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public String addDocument(Model model, 
			@ModelAttribute DocumentDTO documentDto) {
		
		HashTagUtil.extractHashTag(documentDto);
		documentService.insert(documentDto);
		return "redirect:/";
	}

	@RequestMapping(value="/", method=RequestMethod.PUT)
	public String editDocument(Model model,
			@ModelAttribute DocumentDTO documentDto,
			@RequestParam(value="page", defaultValue="0") int page) {
		
		HashTagUtil.extractHashTag(documentDto);
		documentService.update(documentDto);
		return "redirect:/" + documentDto.getId() + "?page="+page;
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteDocument(Model model,
			@PathVariable("id") Long id,
			@RequestParam(value="page", defaultValue="0") int page) {
		documentService.delete(id);
		return "redirect:/?page=" + page;
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String goShow(Model model,
			@PathVariable("id") Long id,
			@RequestParam(value="page", defaultValue="0") int page) {
		DocumentDTO documentDto = documentService.select(id);
		model.addAttribute("document", documentDto);
		model.addAttribute("page", page);
		return "show";
	}

	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String goNew(Model model) {
		DocumentDTO dto = new DocumentDTO();
		model.addAttribute("document", dto);
		model.addAttribute("mode", "new");
		return "regist";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String goEdit(Model model,
			@PathVariable("id") Long id,
			@RequestParam(value="page", defaultValue="0") int page) {
		DocumentDTO documentDto = documentService.select(id);
		model.addAttribute("document", documentDto);
		model.addAttribute("mode", "edit");
		model.addAttribute("page", page);
		return "regist";
	}
	
}
