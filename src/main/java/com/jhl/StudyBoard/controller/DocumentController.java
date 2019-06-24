package com.jhl.StudyBoard.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhl.StudyBoard.dto.DocumentDTO;
import com.jhl.StudyBoard.dto.DocumentListDTO;
import com.jhl.StudyBoard.dto.TagDTO;
import com.jhl.StudyBoard.service.DocumentService;

@Controller
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goList(Model model,
			@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="size", required=false, defaultValue="10") int size) {
		DocumentListDTO result = documentService.selectList(page, size);
		model.addAttribute("list", result.getList());
		model.addAttribute("totalPage", result.getTotalPages());
		model.addAttribute("page", result.getPage());
		return "list";
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public String addDocument(Model model,
			@ModelAttribute DocumentDTO documentDto) {
		
		extractHashTag(documentDto);	// 내용에서 태그 추출 후 태그 리스트를 dto에 담는다.
		documentService.insert(documentDto);
		return "redirect:/";
	}

	@RequestMapping(value="/", method=RequestMethod.PUT)
	public String editDocument(Model model,
			@ModelAttribute DocumentDTO documentDto,
			@RequestParam(value="page", defaultValue="0") int page) {
		
		extractHashTag(documentDto);	// 내용에서 태그 추출 후 태그 리스트를 dto에 담는다.
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
	
	// 내용에서 해시태그 추출 후 태그 리스트를 dto에 담는다.
	public void extractHashTag(DocumentDTO dto) {
		Pattern p = Pattern.compile("\\#([0-9a-zA-Z가-힣ㄱ-ㅎㅏ-ㅣ_]*)");
		Matcher m = p.matcher(dto.getContent());
		String extractText = null;
		
		while(m.find()) {
			extractText = m.group(1);
			if(extractText != null && !extractText.equals("")) {
				dto.addTag(new TagDTO(extractText));
			}
		}
	}
}
