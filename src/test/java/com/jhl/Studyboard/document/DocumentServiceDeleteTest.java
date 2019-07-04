package com.jhl.Studyboard.document;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.exception.DocumentNotFoundException;
import com.jhl.StudyBoard.repository.TagRepository;
import com.jhl.StudyBoard.service.DocumentService;
import com.jhl.Studyboard.data.DocumentData;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DocumentServiceDeleteTest {

	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private TagRepository tagRepository;
	
	private Long savedDocumentId;
	
	@Before
	public void insert() {
		Document saved = documentService.insert(DocumentData.initData());
		savedDocumentId = saved.getId();
	}

	@Test(expected = DocumentNotFoundException.class)
	public void delete() {
		// delete
		documentService.delete(savedDocumentId);

		// tag는 삭제되면 안됨
		assertThat(tagRepository.findAll()).isNotEmpty();
		
		// delete - test
		documentService.select(savedDocumentId);
	}
}
