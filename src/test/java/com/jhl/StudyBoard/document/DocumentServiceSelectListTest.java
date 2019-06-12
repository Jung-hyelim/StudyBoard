package com.jhl.StudyBoard.document;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.service.DocumentService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DocumentServiceSelectListTest {

	@Autowired
	private DocumentService documentService;

	final int PAGE_SIZE = 3;

	Document document1;
	Document document2;
	Document document3;
	Document document4;
	Document document5;
	
	@Before
	public void insert() {
		document1 = new Document(null, "title1", "content1");
		document2 = new Document(null, "title2", "content2");
		document3 = new Document(null, "title3", "content3");
		document4 = new Document(null, "title4", "content4");
		document5 = new Document(null, "title5", "content5");

		documentService.insert(document1);
		documentService.insert(document2);
		documentService.insert(document3);
		documentService.insert(document4);
		documentService.insert(document5);
	}

	@Test
	public void selectList() {
		Page<Document> list1 = documentService.selectList(0, PAGE_SIZE);
		assertThat(list1).isNotEmpty();
		assertThat(list1.getContent().size()).isEqualTo(PAGE_SIZE);
		assertThat(list1.getContent().get(0).getId()).isEqualTo(document5.getId());
		assertThat(list1.getContent().get(0).getTitle()).isEqualTo(document5.getTitle());
		assertThat(list1.getContent().get(0).getContent()).isEqualTo(document5.getContent());
		
		Page<Document> list2 = documentService.selectList(1, PAGE_SIZE);
		assertThat(list2).isNotEmpty();
		assertThat(list2.getContent().size()).isNotEqualTo(PAGE_SIZE);
		
		Page<Document> list3 = documentService.selectList(2, PAGE_SIZE);
		assertThat(list3).isEmpty();
	}
}
