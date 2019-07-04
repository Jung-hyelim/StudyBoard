package com.jhl.Studyboard.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.StudyBoard.dto.DocumentDTO;
import com.jhl.StudyBoard.dto.PhotoDTO;
import com.jhl.StudyBoard.dto.PhotoTextDTO;
import com.jhl.StudyBoard.dto.TagDTO;
import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.entity.DocumentAndTag;
import com.jhl.StudyBoard.entity.Photo;
import com.jhl.StudyBoard.entity.PhotoText;
import com.jhl.StudyBoard.service.DocumentService;
import com.jhl.Studyboard.data.DocumentData;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DocumentServiceSelectTest {

	@Autowired
	private DocumentService documentService;

	private Document data;
	
	@Before
	public void insert() {
		data = documentService.insert(DocumentData.initData());
	}

	@After
	public void after() {
		documentService.delete(data.getId());
	}

	@Test
	public void select() {
		// select
		DocumentDTO selected = documentService.select(data.getId());
		
		// document
		assertThat(selected).isNotNull();
		assertThat(selected.getId()).isEqualTo(data.getId());
		assertThat(selected.getTitle()).isEqualTo(data.getTitle());
		assertThat(selected.getContent()).isEqualTo(data.getContent());
		
		// photos
		List<PhotoDTO> selectedPhotos = selected.getPhotos();
		List<Photo> dataPhotos = data.getPhotos();
		assertThat(selectedPhotos).isNotEmpty();
		assertThat(selectedPhotos.size()).isEqualTo(dataPhotos.size());
		for(int i = 0; i < selectedPhotos.size(); i++){
			assertThat(selectedPhotos.get(i).getFile_path()).isEqualTo(dataPhotos.get(i).getFile_path());
			assertThat(selectedPhotos.get(i).getFile_name()).isEqualTo(dataPhotos.get(i).getFile_name());
			
			// photo texts
			List<PhotoTextDTO> selectedPhotoTexts = selectedPhotos.get(i).getPhoto_texts();
			List<PhotoText> dataPhotoTexts = dataPhotos.get(i).getPhoto_texts();
			assertThat(selectedPhotoTexts).isNotNull();
			assertThat(selectedPhotoTexts.size()).isEqualTo(dataPhotoTexts.size());
			for(int j = 0; j < selectedPhotoTexts.size(); j++){
				assertThat(selectedPhotoTexts.get(j).getPosition_x()).isEqualTo(dataPhotoTexts.get(j).getPosition_x());
				assertThat(selectedPhotoTexts.get(j).getPosition_y()).isEqualTo(dataPhotoTexts.get(j).getPosition_y());
				assertThat(selectedPhotoTexts.get(j).getText()).isEqualTo(dataPhotoTexts.get(j).getText());
			}
		}
		
		// tags
		List<TagDTO> selectedTags = selected.getTags();
		List<DocumentAndTag> dataMappings = data.getMappings();
		assertThat(selectedTags).isNotEmpty();
		assertThat(selectedTags.size()).isEqualTo(dataMappings.size());
		for(int i = 0; i < selectedTags.size(); i++){
			assertThat(selectedTags.get(i).getName()).isEqualTo(dataMappings.get(i).getTag().getName());
		}
	}
}
