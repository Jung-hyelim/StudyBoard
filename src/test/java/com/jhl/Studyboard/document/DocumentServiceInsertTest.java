package com.jhl.StudyBoard.document;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.StudyBoard.data.DocumentData;
import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.service.DocumentService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DocumentServiceInsertTest {

	@Autowired
	private DocumentService documentService;

	@Test
	public void insert() {
		Document document = DocumentData.initData();

		// insert
		Document saved = documentService.insert(document);
		
		// insert test
		assertThat(saved).isNotNull();
		assertThat(saved.getTitle()).isEqualTo(document.getTitle());
		assertThat(saved.getContent()).isEqualTo(document.getContent());
		assertThat(saved.getPhotos()).isNotEmpty();
		assertThat(saved.getPhotos().size()).isEqualTo(DocumentData.PHOTO_INIT_SIZE);
	}
}
