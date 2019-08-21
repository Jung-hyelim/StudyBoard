package com.jhl.studyboard.document;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.studyboard.dto.DocumentDTO;
import com.jhl.studyboard.service.DocumentService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DocumentServiceSelectListTest {

	@Autowired
	private DocumentService documentService;

	final int PAGE_SIZE = 3;
	final int TOTAL_COUNT = 5;

	DocumentDTO document1 = new DocumentDTO();
	DocumentDTO document2 = new DocumentDTO();
	DocumentDTO document3 = new DocumentDTO();
	DocumentDTO document4 = new DocumentDTO();
	DocumentDTO document5 = new DocumentDTO();
	
	@Before
	public void insert() {
		document1.setTitle("title");
		document1.setContent("content");
		document2.setTitle("title");
		document2.setContent("content");
		document3.setTitle("title");
		document3.setContent("content");
		document4.setTitle("title");
		document4.setContent("content");
		document5.setTitle("title");
		document5.setContent("content");

		documentService.insert(document1);
		documentService.insert(document2);
		documentService.insert(document3);
		documentService.insert(document4);
		documentService.insert(document5);
	}

	@Test
	public void selectList() {
		int pageNumber = 0;
		Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE); 
		Page<DocumentDTO> list = documentService.selectList(pageable);
		assertThat(list.getPageable()).isNotNull();
		assertThat(list.getNumber()).isEqualTo(pageNumber);
		assertThat(list.getTotalPages()).isEqualTo((TOTAL_COUNT / PAGE_SIZE) + 1);
		assertThat(list.getContent()).isNotEmpty();
		assertThat(list.getContent().size()).isEqualTo(PAGE_SIZE);
		
		pageNumber = 1;
		pageable = PageRequest.of(pageNumber, PAGE_SIZE); 
		list = documentService.selectList(pageable);
		assertThat(list.getPageable()).isNotNull();
		assertThat(list.getNumber()).isEqualTo(pageNumber);
		assertThat(list.getTotalPages()).isEqualTo((TOTAL_COUNT / PAGE_SIZE) + 1);
		assertThat(list.getContent()).isNotEmpty();
		assertThat(list.getContent().size()).isEqualTo(TOTAL_COUNT - PAGE_SIZE);
		
		pageNumber = 2;
		pageable = PageRequest.of(pageNumber, PAGE_SIZE); 
		list = documentService.selectList(pageable);
		assertThat(list.getContent()).isEmpty();
	}
}
