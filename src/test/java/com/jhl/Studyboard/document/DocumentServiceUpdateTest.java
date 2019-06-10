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
import com.jhl.StudyBoard.entity.PhotoText;
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
		List<Photo> newDocumentPhotos = newDocument.getPhotos();
		List<Photo> updatedPhotos = updated.getPhotos();
		assertThat(updatedPhotos.size()).isEqualTo(newDocumentPhotos.size());
		for(int i = 0; i < newDocumentPhotos.size(); i++) {
			assertThat(updatedPhotos.get(i).getFile_path()).isEqualTo(newDocumentPhotos.get(i).getFile_path());
			assertThat(updatedPhotos.get(i).getFile_name()).isEqualTo(newDocumentPhotos.get(i).getFile_name());
			assertThat(updatedPhotos.get(i).getDocument().getId()).isEqualTo(newDocument.getId());
		}
		
		// update test - photo_texts
		List<PhotoText> newDocumentPhotoTexts = newDocumentPhotos.get(0).getPhoto_texts();
		List<PhotoText> updatedPhotoTexts = updatedPhotos.get(0).getPhoto_texts();
		assertThat(updatedPhotoTexts).isNotEmpty();
		assertThat(updatedPhotoTexts.size()).isEqualTo(newDocumentPhotoTexts.size());
		for(int i = 0; i < newDocumentPhotoTexts.size(); i++) {
			assertThat(updatedPhotoTexts.get(i).getPosition_x()).isEqualTo(newDocumentPhotoTexts.get(i).getPosition_x());
			assertThat(updatedPhotoTexts.get(i).getPosition_y()).isEqualTo(newDocumentPhotoTexts.get(i).getPosition_y());
			assertThat(updatedPhotoTexts.get(i).getText()).isEqualTo(newDocumentPhotoTexts.get(i).getText());
		}
	}
}
