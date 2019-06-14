package com.jhl.StudyBoard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhl.StudyBoard.dto.DocumentDTO;
import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.service.DocumentService;

@Api(description = "페이지 이동 API")
@Controller
@RequestMapping("/")
public class PageController {

	@Autowired
	private DocumentService documentService;

	@ApiOperation(value = "문서 리스트 페이지로 이동")
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goList(Model model,
			@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="size", required=false, defaultValue="10") int size) {
		Page<Document> result = documentService.selectList(page, size);
		model.addAttribute("list", result.getContent());
		model.addAttribute("totalPage", result.getTotalPages());
		model.addAttribute("page", result.getNumber());
		return "list";
	}

	@ApiOperation(value = "문서 저장 후 리스트 페이지로 redirect")
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String addDocument(Model model,
			@ModelAttribute DocumentDTO documentDto) {
		Document document = new Document();
		document.setFromDto(documentDto);
		
		documentService.insert(document);
		return "redirect:/";
	}

	@ApiOperation(value = "문서 수정 후 상세 페이지로 redirect")
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public String editDocument(Model model,
			@ModelAttribute DocumentDTO documentDto,
			@RequestParam(value="page", defaultValue="0") int page) {
		Document document = new Document();
		document.setFromDto(documentDto);
		
		documentService.update(document);
		return "redirect:/" + documentDto.getId() + "?page="+page;
	}

	@ApiOperation(value = "문서 삭제 후 리스트 페이지로 redirect")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteDocument(Model model,
			@PathVariable("id") Long id,
			@RequestParam(value="page", defaultValue="0") int page) {
		documentService.delete(id);
		return "redirect:/?page=" + page;
	}

	@ApiOperation(value = "문서 상세 페이지로 이동")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String goShow(Model model,
			@PathVariable("id") Long id,
			@RequestParam(value="page", defaultValue="0") int page) {
		DocumentDTO documentDto = documentService.select(id);
		model.addAttribute("document", documentDto);
		model.addAttribute("page", page);
		return "show";
	}

	@ApiOperation(value = "새 문서 페이지로 이동")
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String goNew(Model model) {
		model.addAttribute("document", new DocumentDTO());
		model.addAttribute("mode", "new");
		return "regist";
	}
	
	@ApiOperation(value = "문서 수정 페이지로 이동")
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
