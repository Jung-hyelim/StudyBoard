package com.jhl.StudyBoard.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.StudyBoard.data.DocumentData;
import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.entity.Photo;
import com.jhl.StudyBoard.service.DocumentService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DocumentServiceUpdateTest {

	@Autowired
	private DocumentService documentService;

	private Long savedDocumentId;

	@Before
	public void insert() {
		Document saved = documentService.insert(DocumentData.initData());
		savedDocumentId = saved.getId();
	}

	@Test
	public void update() {
		// update data
		Document newDocument = DocumentData.updateData(savedDocumentId);

		// update
		Document updated = documentService.update(newDocument);

		// update test - document
		assertThat(updated).isNotNull();
		assertThat(updated.getId()).isEqualTo(newDocument.getId());
		assertThat(updated.getTitle()).isEqualTo(newDocument.getTitle());
		assertThat(updated.getContent()).isEqualTo(newDocument.getContent());

		// update test - photos
		List<Photo> updatedPhotos = updated.getPhotos();
		assertThat(updatedPhotos.size()).isEqualTo(DocumentData.PHOTO_UPDATE_SIZE);
		assertThat(updatedPhotos.get(0).getFile_name()).isEqualTo(newDocument.getPhotos().get(0).getFile_name());
		assertThat(updatedPhotos.get(0).getDocument().getId()).isEqualTo(newDocument.getId());
		assertThat(updatedPhotos.get(1).getFile_name()).isEqualTo(newDocument.getPhotos().get(1).getFile_name());
		assertThat(updatedPhotos.get(1).getDocument().getId()).isEqualTo(newDocument.getId());
	}
}
