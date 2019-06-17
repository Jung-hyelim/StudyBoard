package com.jhl.StudyBoard.document;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.StudyBoard.dto.DocumentDTO;
import com.jhl.StudyBoard.dto.DocumentListDTO;
import com.jhl.StudyBoard.service.DocumentService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DocumentServiceSelectListTest {

	@Autowired
	private DocumentService documentService;

	final int PAGE_SIZE = 3;

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
		DocumentListDTO list1 = documentService.selectList(0, PAGE_SIZE);
		assertThat(list1.getList()).isNotEmpty();
		assertThat(list1.getList().size()).isEqualTo(PAGE_SIZE);
		assertThat(list1.getList().get(0).getTitle()).isEqualTo(document5.getTitle());
		assertThat(list1.getList().get(0).getContent()).isEqualTo(document5.getContent());
		
		DocumentListDTO list2 = documentService.selectList(1, PAGE_SIZE);
		assertThat(list2.getList()).isNotEmpty();
		assertThat(list2.getList().size()).isNotEqualTo(PAGE_SIZE);
		
		DocumentListDTO list3 = documentService.selectList(2, PAGE_SIZE);
		assertThat(list3.getList()).isEmpty();
	}
}
